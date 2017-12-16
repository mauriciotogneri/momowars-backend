package com.mauriciotogneri.momowars.model;

import com.mauriciotogneri.momowars.types.MatchStatus;

import java.util.List;

public class Match
{
    private final Long id;
    private final MatchStatus status;
    private final Integer maxPlayers;
    private final Map map;
    private final List<Player> players;

    public Match(Long id, MatchStatus status, Integer maxPlayers, Map map, List<Player> players)
    {
        this.id = id;
        this.status = status;
        this.maxPlayers = maxPlayers;
        this.map = map;
        this.players = players;
    }

    public Long id()
    {
        return id;
    }

    public int playersMissing()
    {
        return maxPlayers - players.size();
    }

    public int playesJoined()
    {
        return players.size();
    }

    public boolean isFull()
    {
        return (players.size() == maxPlayers);
    }

    public boolean isOpen()
    {
        return (status == MatchStatus.OPEN);
    }

    public boolean isPlaying()
    {
        return (status == MatchStatus.PLAYING) || (status == MatchStatus.CALCULATING);
    }

    public boolean isFinished()
    {
        return (status == MatchStatus.FINISHED);
    }
}