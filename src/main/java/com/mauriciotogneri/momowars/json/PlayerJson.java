package com.mauriciotogneri.momowars.json;

import com.mauriciotogneri.momowars.types.PlayerStatus;

public class PlayerJson
{
    private final Long id;
    private final String nickname;
    private final Integer resources;
    private final PlayerStatus status;

    public PlayerJson(Long id, String nickname, Integer resources, PlayerStatus status)
    {
        this.id = id;
        this.nickname = nickname;
        this.resources = resources;
        this.status = status;
    }

    public PlayerJson(String nickname, PlayerStatus status)
    {
        this(null, nickname, null, status);
    }
}