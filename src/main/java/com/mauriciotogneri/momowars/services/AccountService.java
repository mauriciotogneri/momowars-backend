package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.exceptions.BadParametersException;
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
            throw new BadParametersException();
        }

        AccountDao accountDao = new AccountDao(connection);

        Account account = accountDao.create(email,
                                            nickname,
                                            SessionService.newSession());

        accountDao.updatePassword(account.id(), password);

        return account;
    }

    public Account getAccount(String session) throws DatabaseException, ApiException
    {
        AccountDao accountDao = new AccountDao(connection);

        return accountDao.bySession(session);
    }

    public Account updateAccount(String session,
                                 String newPassword,
                                 String newNickname) throws DatabaseException, ApiException
    {
        AccountDao accountDao = new AccountDao(connection);

        Account account = getAccount(session);

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