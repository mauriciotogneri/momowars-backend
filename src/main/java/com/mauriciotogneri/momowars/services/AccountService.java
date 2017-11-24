package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.dao.AccountDao;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.AccountAlreadyExistsException;
import com.mauriciotogneri.momowars.exceptions.InvalidParametersException;
import com.mauriciotogneri.momowars.exceptions.InvalidSessionTokenException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.validators.EmailValidator;

public class AccountService
{
    public static Account createAccount(DatabaseConnection connection,
                                        String email,
                                        String nickname,
                                        String password)
            throws AccountAlreadyExistsException, InvalidParametersException
    {
        if (!EmailValidator.isValid(email))
        {
            throw new InvalidParametersException();
        }

        AccountDao accountDao = new AccountDao(connection);

        return accountDao.create(email,
                                 nickname,
                                 password,
                                 SessionService.newSessionToken());
    }

    public static Account getAccount(DatabaseConnection connection,
                                     String sessionToken)
            throws InvalidSessionTokenException, DatabaseException
    {
        AccountDao accountDao = new AccountDao(connection);

        return accountDao.bySessionToken(sessionToken);
    }
}