package com.mauriciotogneri.momowars.repository.player;

import com.mauriciotogneri.momowars.model.Player;
import com.mauriciotogneri.momowars.types.PlayerStatus;

public class PlayerRow
{
    public Long id;
    public Long gameId;
    public Long accountId;
    public String nickname;
    public Integer resources;
    public PlayerStatus status;

    public Player player(Long forAccountId)
    {
        if (accountId.equals(forAccountId))
        {
            return new Player(id, nickname, resources, status);
        }
        else
        {
            return new Player(nickname, status);
        }
    }

    public Player player()
    {
        return new Player(id, nickname, resources, status);
    }
}