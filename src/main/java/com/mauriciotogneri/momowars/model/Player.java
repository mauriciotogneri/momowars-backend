package com.mauriciotogneri.momowars.model;

import com.mauriciotogneri.momowars.types.PlayerStatus;

public class Player
{
    private final Long id;
    private final String nickname;
    private final Integer resources;
    private final PlayerStatus status;

    public Player(Long id, String nickname, Integer resources, PlayerStatus status)
    {
        this.id = id;
        this.nickname = nickname;
        this.resources = resources;
        this.status = status;
    }

    public Player(String nickname, PlayerStatus status)
    {
        this.id = null;
        this.nickname = nickname;
        this.resources = null;
        this.status = status;
    }
}