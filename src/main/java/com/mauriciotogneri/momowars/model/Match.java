package com.mauriciotogneri.momowars.model;

import com.mauriciotogneri.momowars.types.MatchStatus;

import java.util.ArrayList;
import java.util.List;

public class Match
{
    public Long id;
    public MatchStatus status;
    public Integer maxPlayers;
    public Long mapId;
    public List<Player> players = new ArrayList<>();

    public Long id()
    {
        return id;
    }

    public int playersMissing()
    {
        return maxPlayers - players.size();
    }

    public int playersJoined()
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