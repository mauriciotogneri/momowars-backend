package com.mauriciotogneri.momowars.repository.player;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.PlayerQueries;
import com.mauriciotogneri.momowars.model.Constants;

public class PlayerDao
{
    private final DatabaseConnection connection;

    public PlayerDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public void create(Long accountId, Long gameId) throws DatabaseException
    {
        InsertQuery createPlayerQuery = connection.insertQuery(PlayerQueries.CREATE);

        long playerId = createPlayerQuery.execute(
                accountId,
                Constants.INITIAL_RESOURCES
        );

        InsertQuery joinGameQuery = connection.insertQuery(PlayerQueries.JOIN_GAME);

        joinGameQuery.execute(
                gameId,
                playerId
        );
    }
}