package com.mauriciotogneri.momowars.repository.game;

import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.model.Player;
import com.mauriciotogneri.momowars.types.GameStatus;

import java.util.List;

public class GameRow
{
    public Long id;
    public GameStatus status;
    public Integer maxPlayers;
    public Long mapId;

    public Game game(Map map, List<Player> players)
    {
        return new Game(id, status, maxPlayers, map, players);
    }

    public boolean isOpen()
    {
        return (status == GameStatus.OPEN);
    }

    public boolean isPlaying()
    {
        return (status == GameStatus.PLAYING);
    }

    public boolean isFinished()
    {
        return (status == GameStatus.FINISHED);
    }
}