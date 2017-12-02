package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.InvalidParametersException;
import com.mauriciotogneri.momowars.exceptions.MapNotFoundException;
import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.repository.game.GameDao;

import java.util.List;

public class GameService
{
    public static Game createGame(DatabaseConnection connection,
                                  Integer maxPlayers,
                                  Long mapId)
            throws InvalidParametersException, MapNotFoundException, DatabaseException
    {
        if (maxPlayers < 2)
        {
            throw new InvalidParametersException();
        }

        Map map = MapService.getMap(connection, mapId);

        GameDao accountDao = new GameDao(connection);

        return accountDao.create(maxPlayers, map);
    }

    public static List<Game> getOpenGames(DatabaseConnection connection)
            throws DatabaseException, MapNotFoundException
    {
        GameDao accountDao = new GameDao(connection);

        return accountDao.getOpenGames();
    }
}