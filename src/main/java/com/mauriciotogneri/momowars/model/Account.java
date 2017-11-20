package com.mauriciotogneri.momowars.model;

public class Account
{
    private final Long id;
    private final String email;
    private final String nickname;

    public Account(Long id, String email, String nickname)
    {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    public Long id()
    {
        return id;
    }
}