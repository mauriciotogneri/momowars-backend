package com.mauriciotogneri.momowars.repository.game;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.QueryResult;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.GameQueries;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.exceptions.GameNotFoundException;
import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.repository.map.MapDao;
import com.mauriciotogneri.momowars.repository.player.PlayerDao;

import java.util.ArrayList;
import java.util.List;

public class GameDao
{
    private final DatabaseConnection connection;

    public GameDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public Game create(Integer maxPlayers, Map map) throws DatabaseException, ApiException
    {
        InsertQuery query = connection.insertQuery(GameQueries.CREATE);

        long id = query.execute(
                maxPlayers,
                map.id()
        );

        return getGame(id);
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
                    playerDao.getPlayers(game.id)
            ));
        }

        return games;
    }

    public Game getGame(Long id) throws DatabaseException, ApiException
    {
        MapDao mapDao = new MapDao(connection);
        PlayerDao playerDao = new PlayerDao(connection);

        SelectQuery<GameRow> query = connection.selectQuery(GameQueries.SELECT_BY_ID, GameRow.class);
        QueryResult<GameRow> result = query.execute(id);

        if (result.hasElements())
        {
            GameRow row = result.first();

            return row.game(
                    mapDao.getMapFull(row.mapId),
                    playerDao.getPlayers(row.id)
            );
        }
        else
        {
            throw new GameNotFoundException();
        }
    }
}