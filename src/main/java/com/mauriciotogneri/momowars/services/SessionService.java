package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.repository.account.AccountDao;
import com.mauriciotogneri.momowars.util.Hash;

import java.util.UUID;

public class SessionService
{
    public static String createSession(DatabaseConnection connection,
                                       String email,
                                       String password) throws DatabaseException, ApiException
    {
        AccountDao accountDao = new AccountDao(connection);
        Account account = accountDao.byCredentials(email, password);

        String sessionToken = newSessionToken();

        accountDao.updateSessionToken(account.id(), sessionToken);

        return sessionToken;
    }

    public static String newSessionToken()
    {
        return Hash.of(UUID.randomUUID().toString());
    }
}