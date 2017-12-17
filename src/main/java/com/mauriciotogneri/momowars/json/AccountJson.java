package com.mauriciotogneri.momowars.json;

public class AccountJson
{
    private final Long id;
    private final String email;
    private final String nickname;
    private final AccountMatchesJson matches;

    public AccountJson(Long id, String email, String nickname, AccountMatchesJson matches)
    {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.matches = matches;
    }
}