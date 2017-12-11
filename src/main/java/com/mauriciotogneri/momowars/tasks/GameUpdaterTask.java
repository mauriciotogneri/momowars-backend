package com.mauriciotogneri.momowars.tasks;

import com.mauriciotogneri.momowars.database.DatabaseConnection;

public class GameUpdaterTask extends Task
{
    private final Long gameId;

    public GameUpdaterTask(Long gameId)
    {
        this.gameId = gameId;
    }

    @Override
    public void process(DatabaseConnection connection) throws Exception
    {
        // TODO
        // create a table to store the before and after the update
        // id   game    before      after       duration

        //GameDao gameDao = new GameDao(connection);
        //gameDao.updateStatus(gameId, GameStatus.PLAYING);
    }
}