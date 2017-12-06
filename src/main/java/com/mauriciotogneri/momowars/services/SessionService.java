package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.jerry.exceptions.client.UnauthorizedException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.federation.FederationEntity;
import com.mauriciotogneri.momowars.federation.GoogleIdentity;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.repository.account.AccountDao;
import com.mauriciotogneri.momowars.util.Hash;

import java.util.Optional;
import java.util.UUID;

public class SessionService
{
    public static String createSessionEmail(DatabaseConnection connection,
                                            String email,
                                            String password) throws DatabaseException, ApiException
    {
        AccountDao accountDao = new AccountDao(connection);
        Account account = accountDao.byCredentials(email, password);

        String sessionToken = newSessionToken();

        accountDao.updateSessionToken(account.id(), sessionToken);

        return sessionToken;
    }

    public static String createSessionGoogle(DatabaseConnection connection,
                                             String token) throws DatabaseException, ApiException
    {
        GoogleIdentity googleIdentity = new GoogleIdentity(token);
        Optional<FederationEntity> entity = googleIdentity.entity();

        if (entity.isPresent())
        {
            // TODO
            AccountDao accountDao = new AccountDao(connection);
            Account account = accountDao.byCredentials("", "");

            String sessionToken = newSessionToken();

            accountDao.updateSessionToken(account.id(), sessionToken);

            return sessionToken;
        }
        else
        {
            throw new UnauthorizedException();
        }
    }

    public static String newSessionToken()
    {
        return Hash.of(UUID.randomUUID().toString());
    }
}