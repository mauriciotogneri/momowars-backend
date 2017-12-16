package com.mauriciotogneri.momowars.api.model.matches;

import com.mauriciotogneri.jsonschema.annotations.Optional;
import com.mauriciotogneri.momowars.types.MatchStatus;

public class Match
{
    public Long id;

    public MatchStatus status;

    public Map map;

    public Integer maxPlayers;

    @Optional
    public Player[] players;
}