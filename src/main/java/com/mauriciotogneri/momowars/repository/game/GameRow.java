package com.mauriciotogneri.momowars.repository.game;

import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.types.GameStatus;

public class GameRow
{
    public Long id;
    public GameStatus status;
    public Integer maxPlayers;
    public Long mapId;

    public Game game(Map map)
    {
        return new Game(id, status, maxPlayers, map);
    }
}