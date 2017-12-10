package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.notifications.GameStarted;
import com.mauriciotogneri.momowars.notifications.Notification;
import com.mauriciotogneri.momowars.repository.game.GameDao;
import com.mauriciotogneri.momowars.repository.map.MapDao;
import com.mauriciotogneri.momowars.types.GameStatus;

import java.util.List;

public class GameService
{
    private final DatabaseConnection connection;

    public GameService(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public Game createGame(Integer maxPlayers,
                           Long mapId,
                           Long forAccountId) throws DatabaseException, ApiException
    {
        MapDao mapDao = new MapDao(connection);
        Map map = mapDao.getMap(mapId);

        GameDao gameDao = new GameDao(connection);

        return gameDao.create(maxPlayers, map, forAccountId);
    }

    public List<Game> getOpenGames() throws DatabaseException, ApiException
    {
        GameDao gameDao = new GameDao(connection);

        return gameDao.getOpenGames();
    }

    public Game getGame(Long gameId,
                        Long forAccountId) throws DatabaseException, ApiException
    {
        GameDao gameDao = new GameDao(connection);

        return gameDao.getGame(gameId, forAccountId);
    }

    public void startGame(Long gameId) throws DatabaseException
    {
        GameDao gameDao = new GameDao(connection);
        gameDao.updateStatus(gameId, GameStatus.PLAYING);

        Notification notification = new GameStarted(gameId);
        notification.send();
    }
}