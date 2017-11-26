package com.mauriciotogneri.momowars.model;

public class Account
{
    private final Long id;
    private final String email;
    private final String nickname;
    private final Long[] games;

    public Account(Long id, String email, String nickname, Long[] games)
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
}