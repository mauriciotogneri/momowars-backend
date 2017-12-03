package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.exceptions.InvalidParametersException;
import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.repository.game.GameDao;

import java.util.List;

public class GameService
{
    public static Game createGame(DatabaseConnection connection,
                                  Integer maxPlayers,
                                  Long mapId) throws DatabaseException, ApiException
    {
        if (maxPlayers < 2)
        {
            throw new InvalidParametersException();
        }

        Map map = MapService.getMap(connection, mapId);

        GameDao gameDao = new GameDao(connection);

        return gameDao.create(maxPlayers, map);
    }

    public static List<Game> getOpenGames(DatabaseConnection connection) throws DatabaseException, ApiException
    {
        GameDao gameDao = new GameDao(connection);

        return gameDao.getOpenGames();
    }

    public static Game getGame(DatabaseConnection connection,
                               Long id) throws DatabaseException, ApiException
    {
        GameDao gameDao = new GameDao(connection);

        return gameDao.getGame(id);
    }
}