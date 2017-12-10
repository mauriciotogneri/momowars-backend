package com.mauriciotogneri.momowars.repository.account;

import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.AccountGames;

public class AccountRow
{
    public Long id;
    public String email;
    public String nickname;
    public String password;
    public String session;

    public Account account(AccountGames games)
    {
        return new Account(id, email, nickname, games);
    }
}