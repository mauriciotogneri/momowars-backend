package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.javautils.Strings;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.AccountNotFoundException;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.exceptions.InvalidCredentialsException;
import com.mauriciotogneri.momowars.exceptions.InvalidSessionException;
import com.mauriciotogneri.momowars.federation.FederationIdentity;
import com.mauriciotogneri.momowars.federation.GoogleIdentity;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.repository.AccountDao;
import com.mauriciotogneri.momowars.util.Hash;

import java.util.Optional;
import java.util.UUID;

public class SessionService
{
    private final DatabaseConnection connection;

    public SessionService(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public String createSessionEmail(String email,
                                     String password) throws DatabaseException, ApiException
    {
        AccountDao accountDao = new AccountDao(connection);
        Account account = accountDao.byEmail(email);

        if (!Strings.equals(account.password, Hash.of(password)))
        {
            throw new InvalidCredentialsException();
        }

        String session = newSession();

        accountDao.updateSession(account.id, session);

        return session;
    }

    public String createSessionGoogle(String token) throws DatabaseException, ApiException
    {
        GoogleIdentity googleIdentity = new GoogleIdentity(token);
        Optional<FederationIdentity> federationIdentity = googleIdentity.identity();

        if (federationIdentity.isPresent())
        {
            FederationIdentity identity = federationIdentity.get();

            AccountDao accountDao = new AccountDao(connection);

            String session = newSession();

            try
            {
                Account account = accountDao.byEmail(identity.email());

                accountDao.updateNickname(account.id, identity.name());
                accountDao.updatePicture(account.id, identity.picture());
                accountDao.updateSession(account.id, session);
            }
            catch (AccountNotFoundException e)
            {
                Account account = accountDao.create(identity.email(), identity.name(), session);
                accountDao.updatePicture(account.id, identity.picture());
            }

            return session;
        }
        else
        {
            throw new InvalidSessionException();
        }
    }

    public static String newSession()
    {
        return Hash.of(UUID.randomUUID().toString());
    }
}