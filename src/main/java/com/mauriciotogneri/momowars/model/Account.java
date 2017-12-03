package com.mauriciotogneri.momowars.model;

import java.util.List;

public class Account
{
    private final Long id;
    private final String email;
    private final String nickname;
    private final List<Long> games;

    public Account(Long id, String email, String nickname, List<Long> games)
    {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.games = games;
    }

    public Long id()
    {
        return id;
    }

    public boolean hasGame(Long gameId)
    {
        for (Long id : games)
        {
            if (id.equals(gameId))
            {
                return true;
            }
        }

        return false;
    }
}