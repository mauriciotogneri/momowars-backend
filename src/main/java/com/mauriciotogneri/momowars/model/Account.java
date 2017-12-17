package com.mauriciotogneri.momowars.model;

import com.mauriciotogneri.momowars.json.AccountJson;

public class Account
{
    public Long id;
    public String email;
    public String nickname;
    public String password;
    public String picture;
    public String session;

    public AccountJson json(AccountMatches matches)
    {
        return new AccountJson(id,
                               email,
                               nickname,
                               matches.json());
    }
}