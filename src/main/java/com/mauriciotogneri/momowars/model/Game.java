package com.mauriciotogneri.momowars.model;

import com.mauriciotogneri.momowars.types.GameStatus;

public class Game
{
    private final Long id;
    private final GameStatus status;
    private final Integer maxPlayers;
    private final Map map;

    public Game(Long id, GameStatus status, Integer maxPlayers, Map map)
    {
        this.id = id;
        this.status = status;
        this.maxPlayers = maxPlayers;
        this.map = map;
    }
}