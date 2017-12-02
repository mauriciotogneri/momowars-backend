package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.model.Player;
import com.mauriciotogneri.momowars.repository.player.PlayerDao;

public class PlayerService
{
    public static void create(DatabaseConnection connection,
                              Long accountId,
                              Long gameId) throws DatabaseException
    {
        PlayerDao playerDao = new PlayerDao(connection);

        Player player = playerDao.create(accountId);

        playerDao.joinGame(gameId, player.id());
    }
}