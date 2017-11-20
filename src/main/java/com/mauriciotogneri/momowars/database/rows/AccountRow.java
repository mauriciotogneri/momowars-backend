package com.mauriciotogneri.momowars.database.rows;

import com.mauriciotogneri.momowars.model.Account;

public class AccountRow
{
    public Long id;
    public String email;
    public String nickname;
    public String password;
    public String session;

    public Account account()
    {
        return new Account(id, email, nickname);
    }
}