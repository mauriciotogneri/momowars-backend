package com.mauriciotogneri.momowars.repository.game;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.QueryResult;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.GameQueries;
import com.mauriciotogneri.momowars.exceptions.GameNotFoundException;
import com.mauriciotogneri.momowars.exceptions.MapNotFoundException;
import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.repository.map.MapDao;
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

    public Game create(Integer maxPlayers, Map map) throws DatabaseException
    {
        InsertQuery query = connection.insertQuery(GameQueries.CREATE);

        long id = query.execute(
                maxPlayers,
                map.id()
        );

        return new Game(id, GameStatus.OPEN, maxPlayers, map);
    }

    public List<Game> getOpenGames() throws DatabaseException, MapNotFoundException
    {
        MapDao mapDao = new MapDao(connection);

        SelectQuery<GameRow> query = connection.selectQuery(GameQueries.SELECT_OPEN, GameRow.class);
        QueryResult<GameRow> result = query.execute();

        List<Game> games = new ArrayList<>();

        for (GameRow game : result)
        {
            games.add(game.game(mapDao.getMap(game.mapId)));
        }

        return games;
    }

    public Game getGame(Long id) throws DatabaseException, MapNotFoundException, GameNotFoundException
    {
        MapDao mapDao = new MapDao(connection);

        SelectQuery<GameRow> query = connection.selectQuery(GameQueries.SELECT_BY_ID, GameRow.class);
        QueryResult<GameRow> result = query.execute(id);

        if (result.hasElements())
        {
            GameRow row = result.first();

            return row.game(mapDao.getMap(row.mapId));
        }
        else
        {
            throw new GameNotFoundException();
        }
    }
}