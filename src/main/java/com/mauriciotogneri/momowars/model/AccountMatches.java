package com.mauriciotogneri.momowars.model;

import java.util.List;

public class AccountMatches
{
    private final List<Long> open;
    private final List<Long> playing;
    private final List<Long> finished;

    public AccountMatches(List<Long> open, List<Long> playing, List<Long> finished)
    {
        this.open = open;
        this.playing = playing;
        this.finished = finished;
    }

    public boolean hasMatch(Long matchId)
    {
        return open.stream().anyMatch(matchId::equals) ||
                playing.stream().anyMatch(matchId::equals) ||
                finished.stream().anyMatch(matchId::equals);
    }
}