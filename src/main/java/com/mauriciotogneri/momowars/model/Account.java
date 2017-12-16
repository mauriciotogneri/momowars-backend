package com.mauriciotogneri.momowars.model;

public class Account
{
    private final Long id;
    private final String email;
    private final String nickname;
    private final AccountMatches matches;

    public Account(Long id, String email, String nickname, AccountMatches matches)
    {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.matches = matches;
    }

    public Long id()
    {
        return id;
    }

    public String email()
    {
        return email;
    }

    public String nickname()
    {
        return nickname;
    }

    public boolean hasMatch(Long matchId)
    {
        return matches.hasMatch(matchId);
    }
}