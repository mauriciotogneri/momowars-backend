package com.mauriciotogneri.momowars.tasks;

public class GameUpdater extends Task
{
    private final Long gameId;

    public GameUpdater(Long gameId)
    {
        this.gameId = gameId;
    }

    @Override
    public void run()
    {
        // TODO
        // create a table to store the before and after the update
        // id   game    before      after       duration
    }
}