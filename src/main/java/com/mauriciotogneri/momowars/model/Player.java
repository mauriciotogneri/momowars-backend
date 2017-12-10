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

    public boolean isPlaying()
    {
        return (status == PlayerStatus.PLAYING);
    }

    public boolean isWaiting()
    {
        return (status == PlayerStatus.WAITING);
    }

    public boolean isVictorious()
    {
        return (status == PlayerStatus.VICTORIOUS);
    }

    public boolean isDefeated()
    {
        return (status == PlayerStatus.DEFEATED);
    }

    public boolean isSurrendered()
    {
        return (status == PlayerStatus.SURRENDERED);
    }
}