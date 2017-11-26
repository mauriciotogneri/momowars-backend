package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.javautils.Encoding;
import com.mauriciotogneri.jerry.exceptions.server.InternalServerErrorException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.InvalidCredentialsException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.repository.account.AccountDao;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class SessionService
{
    public static String createSession(DatabaseConnection connection, String email, String password) throws InvalidCredentialsException, DatabaseException
    {
        AccountDao accountDao = new AccountDao(connection);
        Account account = accountDao.byCredentials(email, password);

        String sessionToken = newSessionToken();

        accountDao.updateSessionToken(account.id(), sessionToken);

        return sessionToken;
    }

    public static String newSessionToken()
    {
        try
        {
            return Encoding.sha512(UUID.randomUUID().toString());
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new InternalServerErrorException(e);
        }
    }
}