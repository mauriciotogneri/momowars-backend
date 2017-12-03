package com.mauriciotogneri.momowars.api.model.games;

import com.mauriciotogneri.jsonschema.annotations.Optional;
import com.mauriciotogneri.momowars.types.GameStatus;

public class Game
{
    public Long id;

    public GameStatus status;

    public Map map;

    public Integer maxPlayers;

    @Optional
    public Player[] players;
}