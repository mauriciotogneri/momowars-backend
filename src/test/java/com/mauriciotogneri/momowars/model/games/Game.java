package com.mauriciotogneri.momowars.model.games;

import com.mauriciotogneri.momowars.types.GameStatus;

public class Game
{
    public Long id;

    public GameStatus status;

    public Map map;

    public Integer maxPlayers;

    public Player[] players;
}