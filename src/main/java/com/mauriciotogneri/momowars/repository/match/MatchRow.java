package com.mauriciotogneri.momowars.repository.match;

import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.model.Match;
import com.mauriciotogneri.momowars.model.Player;
import com.mauriciotogneri.momowars.types.MatchStatus;

import java.util.List;

public class MatchRow
{
    public Long id;
    public MatchStatus status;
    public Integer maxPlayers;
    public Long mapId;

    public Match match(Map map, List<Player> players)
    {
        return new Match(id, status, maxPlayers, map, players);
    }

    public boolean isOpen()
    {
        return (status == MatchStatus.OPEN);
    }

    public boolean isPlaying()
    {
        return (status == MatchStatus.PLAYING);
    }

    public boolean isFinished()
    {
        return (status == MatchStatus.FINISHED);
    }
}