package com.mauriciotogneri.momowars.notifications;

public class GameStarted implements Notification
{
    private final Long gameId;

    public GameStarted(Long gameId)
    {
        this.gameId = gameId;
    }

    @Override
    public void send()
    {
        // TODO: send a notification to all players
    }
}