package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.exceptions.InvalidParametersException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.repository.account.AccountDao;
import com.mauriciotogneri.momowars.validators.EmailValidator;

public class AccountService
{
    private final DatabaseConnection connection;

    public AccountService(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public Account createAccount(String email,
                                 String nickname,
                                 String password) throws DatabaseException, ApiException
    {
        if (!EmailValidator.isValid(email))
        {
            throw new InvalidParametersException();
        }

        AccountDao accountDao = new AccountDao(connection);

        Account account = accountDao.create(email,
                                            nickname,
                                            SessionService.newSessionToken());

        accountDao.updatePassword(account.id(), password);

        return account;
    }

    public Account getAccount(String sessionToken) throws DatabaseException, ApiException
    {
        AccountDao accountDao = new AccountDao(connection);

        return accountDao.bySessionToken(sessionToken);
    }

    public Account updateAccount(String sessionToken,
                                 String newPassword,
                                 String newNickname) throws DatabaseException, ApiException
    {
        AccountDao accountDao = new AccountDao(connection);

        Account account = getAccount(sessionToken);

        if (newPassword != null)
        {
            accountDao.updatePassword(account.id(), newPassword);
        }

        if (newNickname != null)
        {
            accountDao.updateNickname(account.id(), newNickname);
        }

        return accountDao.getAccount(account.id());
    }
}