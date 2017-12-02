package com.mauriciotogneri.momowars.repository.player;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.AccountQueries;
import com.mauriciotogneri.momowars.database.SQL.PlayerQueries;
import com.mauriciotogneri.momowars.model.Constants;
import com.mauriciotogneri.momowars.model.Player;
import com.mauriciotogneri.momowars.types.PlayerStatus;

public class PlayerDao
{
    private final DatabaseConnection connection;

    public PlayerDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public Player create(Long accountId) throws DatabaseException
    {
        InsertQuery query = connection.insertQuery(PlayerQueries.INSERT);

        long id = query.execute(
                accountId,
                Constants.INITIAL_RESOURCES
        );

        return new Player(id, Constants.INITIAL_RESOURCES, PlayerStatus.WAITING);
    }

    public void joinGame(Long gameId, Long playerId) throws DatabaseException
    {
        InsertQuery query = connection.insertQuery(AccountQueries.JOIN_GAME);

        query.execute(
                gameId,
                playerId
        );
    }
}