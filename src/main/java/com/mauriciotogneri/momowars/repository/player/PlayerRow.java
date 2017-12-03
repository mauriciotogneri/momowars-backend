package com.mauriciotogneri.momowars.repository.player;

import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.Player;
import com.mauriciotogneri.momowars.types.PlayerStatus;

public class PlayerRow
{
    public Long id;
    public Long accountId;
    public Integer resources;
    public PlayerStatus status;

    public Player player(Account account)
    {
        return new Player(id, account.nickname(), resources, status);
    }
}