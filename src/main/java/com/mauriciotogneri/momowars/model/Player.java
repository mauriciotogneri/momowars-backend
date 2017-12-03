package com.mauriciotogneri.momowars.model;

import com.mauriciotogneri.momowars.types.PlayerStatus;

public class Player
{
    private final Long id;
    private final Integer resources;
    private final PlayerStatus status;

    public Player(Long id, Integer resources, PlayerStatus status)
    {
        this.id = id;
        this.resources = resources;
        this.status = status;
    }
}