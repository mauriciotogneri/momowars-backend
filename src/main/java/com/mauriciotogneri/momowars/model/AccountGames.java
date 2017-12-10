package com.mauriciotogneri.momowars.model;

import java.util.List;

public class AccountGames
{
    private final List<Long> open;
    private final List<Long> playing;
    private final List<Long> finished;

    public AccountGames(List<Long> open, List<Long> playing, List<Long> finished)
    {
        this.open = open;
        this.playing = playing;
        this.finished = finished;
    }

    public boolean hasGame(Long gameId)
    {
        return open.stream().anyMatch(gameId::equals) ||
                playing.stream().anyMatch(gameId::equals) ||
                finished.stream().anyMatch(gameId::equals);
    }
}