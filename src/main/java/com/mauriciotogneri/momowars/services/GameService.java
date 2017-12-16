package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.exceptions.GameFinishedException;
import com.mauriciotogneri.momowars.exceptions.GamePlayingException;
import com.mauriciotogneri.momowars.exceptions.PlayerAlreadyJoinedException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.repository.game.GameDao;
import com.mauriciotogneri.momowars.repository.map.MapDao;
import com.mauriciotogneri.momowars.tasks.GameStartedTask;
import com.mauriciotogneri.momowars.tasks.Task;
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

    public Game joinGame(Account account,
                         Long gameId) throws DatabaseException, ApiException
    {
        if (account.hasGame(gameId))
        {
            throw new PlayerAlreadyJoinedException();
        }

        Game loadedGame = getGame(gameId, account.id());

        if (loadedGame.isPlaying())
        {
            throw new GamePlayingException();
        }
        else if (loadedGame.isFinished())
        {
            throw new GameFinishedException();
        }

        PlayerService playerService = new PlayerService(connection);
        playerService.create(account.id(), loadedGame.id());

        if (loadedGame.playersMissing() == 1)
        {
            startGame(loadedGame.id());
        }

        return getGame(loadedGame.id(), account.id());
    }

    public void startGame(Long gameId) throws DatabaseException
    {
        GameDao gameDao = new GameDao(connection);
        gameDao.updateStatus(gameId, GameStatus.PLAYING);

        Task task = new GameStartedTask(gameId);
        task.submit();
    }
}