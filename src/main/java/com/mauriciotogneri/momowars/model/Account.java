package com.mauriciotogneri.momowars.model;

public class Account
{
    private final Long id;
    private final String email;
    private final String nickname;
    private final AccountGames games;

    public Account(Long id, String email, String nickname, AccountGames games)
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

    public String nickname()
    {
        return nickname;
    }

    public boolean hasGame(Long gameId)
    {
        return games.hasGame(gameId);
    }
}