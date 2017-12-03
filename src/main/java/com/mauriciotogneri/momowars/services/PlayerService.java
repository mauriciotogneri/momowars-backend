package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.repository.player.PlayerDao;

public class PlayerService
{
    public static void create(DatabaseConnection connection,
                              Long accountId,
                              Long gameId) throws DatabaseException
    {
        PlayerDao playerDao = new PlayerDao(connection);

        playerDao.create(accountId, gameId);
    }
}