package com.mauriciotogneri.momowars.repository.game;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.QueryResult;
import com.mauriciotogneri.inquiry.queries.DeleteQuery;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.inquiry.queries.UpdateQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.GameQueries;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.exceptions.GameNotFoundException;
import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.model.Player;
import com.mauriciotogneri.momowars.repository.map.MapDao;
import com.mauriciotogneri.momowars.repository.player.PlayerDao;
import com.mauriciotogneri.momowars.types.GameStatus;

import java.util.ArrayList;
import java.util.List;

public class GameDao
{
    private final DatabaseConnection connection;

    public GameDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public Game create(Integer maxPlayers, Map map, Long forAccountId) throws DatabaseException, ApiException
    {
        InsertQuery query = connection.insertQuery(GameQueries.CREATE);

        long gameId = query.execute(
                maxPlayers,
                map.id()
        );

        return getGame(gameId, forAccountId);
    }

    public List<Game> getOpenGames() throws DatabaseException, ApiException
    {
        MapDao mapDao = new MapDao(connection);
        PlayerDao playerDao = new PlayerDao(connection);

        SelectQuery<GameRow> query = connection.selectQuery(GameQueries.SELECT_OPEN, GameRow.class);
        QueryResult<GameRow> result = query.execute();

        List<Game> games = new ArrayList<>();

        for (GameRow game : result)
        {
            games.add(game.game(
                    mapDao.getMap(game.mapId),
                    playerDao.getPlayers(game.id, 0L)
            ));
        }

        return games;
    }

    public Game getGame(Long gameId, Long forAccountId) throws DatabaseException, ApiException
    {
        MapDao mapDao = new MapDao(connection);
        PlayerDao playerDao = new PlayerDao(connection);

        SelectQuery<GameRow> query = connection.selectQuery(GameQueries.SELECT_BY_ID, GameRow.class);
        QueryResult<GameRow> result = query.execute(gameId);

        if (result.hasElements())
        {
            GameRow row = result.first();

            return row.game(
                    mapDao.getMapFull(row.mapId),
                    playerDao.getPlayers(row.id, forAccountId)
            );
        }
        else
        {
            throw new GameNotFoundException();
        }
    }

    public void updateStatus(Long gameId, GameStatus status) throws DatabaseException
    {
        UpdateQuery query = connection.updateQuery(GameQueries.UPDATE_STATUS);

        int rowsAffected = query.execute(status, gameId);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }

    public boolean isUpdatable(Long gameId) throws DatabaseException
    {
        PlayerDao playerDao = new PlayerDao(connection);

        List<Player> players = playerDao.getPlayers(gameId, null);

        for (Player player : players)
        {
            if (player.isPlaying())
            {
                return false;
            }
        }

        return true;
    }

    public void delete(Long playerId) throws DatabaseException
    {
        DeleteQuery query = connection.deleteQuery(GameQueries.DELETE);

        int rowsAffected = query.execute(playerId);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }
}