package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.repository.player.PlayerDao;

public class PlayerService
{
    private final DatabaseConnection connection;

    public PlayerService(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public void create(Long accountId,
                       Long matchId) throws DatabaseException
    {
        PlayerDao playerDao = new PlayerDao(connection);

        playerDao.create(accountId, matchId);
    }

    public void endTurn(Long matchId,
                        Long playerId,
                        Long accountId) throws DatabaseException, ApiException
    {
        PlayerDao playerDao = new PlayerDao(connection);

        playerDao.endTurn(matchId, playerId, accountId);
    }

    public void leaveMatch(Long matchId,
                           Long playerId,
                           Long accountId) throws DatabaseException, ApiException
    {
        PlayerDao playerDao = new PlayerDao(connection);

        playerDao.leaveMatch(matchId, playerId, accountId);
    }
}