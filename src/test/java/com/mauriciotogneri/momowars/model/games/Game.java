package com.mauriciotogneri.momowars.model.games;

import com.mauriciotogneri.momowars.model.constants.GameStatus;

public class Game
{
    public GameStatus status;

    public Map map;

    public Player[] players;

    public Cell[] cells;
}
