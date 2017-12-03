package com.mauriciotogneri.momowars.model;

import com.mauriciotogneri.momowars.types.GameStatus;

import java.util.List;

public class Game
{
    private final Long id;
    private final GameStatus status;
    private final Integer maxPlayers;
    private final Map map;
    private final List<Player> players;

    public Game(Long id, GameStatus status, Integer maxPlayers, Map map, List<Player> players)
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

    public boolean isFull()
    {
        return (players.size() == maxPlayers);
    }

    public boolean isOpen()
    {
        return (status == GameStatus.OPEN);
    }
}