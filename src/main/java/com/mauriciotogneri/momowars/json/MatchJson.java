package com.mauriciotogneri.momowars.json;

import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.model.Player;
import com.mauriciotogneri.momowars.types.MatchStatus;

import java.util.List;

public class MatchJson
{
    private final Long id;
    private final MatchStatus status;
    private final Integer maxPlayers;
    private final Map map;
    private final List<Player> players;

    public MatchJson(Long id, MatchStatus status, Integer maxPlayers, Map map, List<Player> players)
    {
        this.id = id;
        this.status = status;
        this.maxPlayers = maxPlayers;
        this.map = map;
        this.players = players;
    }
}