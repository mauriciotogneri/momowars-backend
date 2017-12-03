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
    public static Account createAccount(DatabaseConnection connection,
                                        String email,
                                        String nickname,
                                        String password) throws ApiException
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
                                     String sessionToken) throws DatabaseException, ApiException
    {
        AccountDao accountDao = new AccountDao(connection);

        return accountDao.bySessionToken(sessionToken);
    }

    public static Account updateAccount(DatabaseConnection connection,
                                        String sessionToken,
                                        String newPassword,
                                        String newNickname) throws DatabaseException, ApiException
    {
        AccountDao accountDao = new AccountDao(connection);

        Account account = getAccount(connection, sessionToken);

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

    public static void joinGame(DatabaseConnection connection,
                                Long accountId,
                                Long gameId) throws DatabaseException
    {
        AccountDao accountDao = new AccountDao(connection);

        accountDao.joinGame(accountId, gameId);
    }
}