package com.mauriciotogneri.momowars.repository.game;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.GameQueries;
import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.types.GameStatus;

public class GameDao
{
    private final DatabaseConnection connection;

    public GameDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public Game create(Integer maxPlayers, Map map) throws DatabaseException
    {
        InsertQuery query = connection.insertQuery(GameQueries.INSERT);

        long id = query.execute(
                maxPlayers,
                map.id()
        );

        return new Game(id, GameStatus.OPEN, maxPlayers, map);
    }
}