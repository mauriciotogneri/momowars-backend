package com.mauriciotogneri.momowars.dao;

import com.mauriciotogneri.momowars.database.DatabaseException;
import com.mauriciotogneri.momowars.database.SQL.AccountQueries;
import com.mauriciotogneri.momowars.database.rows.AccountRow;
import com.mauriciotogneri.momowars.database.sql.InsertQuery;
import com.mauriciotogneri.momowars.database.sql.QueryResult;
import com.mauriciotogneri.momowars.database.sql.SelectQuery;
import com.mauriciotogneri.momowars.database.sql.UpdateQuery;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.exceptions.AccountAlreadyExistsException;
import com.mauriciotogneri.momowars.model.exceptions.AccountNotFoundException;
import com.mauriciotogneri.momowars.utils.SHA;

import java.io.IOException;

public class AccountDao
{
    public static Account bySessionToken(String sessionToken) throws AccountNotFoundException, DatabaseException
    {
        SelectQuery<AccountRow> query = new SelectQuery<>(AccountQueries.SELECT_BY_SESSION_TOKEN, AccountRow.class);
        QueryResult<AccountRow> result = query.execute(sessionToken);

        if (result.hasRows())
        {
            return result.row().account();
        }
        else
        {
            throw new AccountNotFoundException();
        }
    }

    public static Account byCredentials(String email, String password) throws AccountNotFoundException, DatabaseException, IOException
    {
        SelectQuery<AccountRow> query = new SelectQuery<>(AccountQueries.SELECT_BY_CREDENTIALS, AccountRow.class);

        QueryResult<AccountRow> result = query.execute(email, SHA.sha512(password));

        if (result.hasRows())
        {
            return result.row().account();
        }
        else
        {
            throw new AccountNotFoundException();
        }
    }

    public static void updateSessionToken(Long id, String sessionToken) throws DatabaseException
    {
        UpdateQuery query = new UpdateQuery(AccountQueries.UPDATE_SESSION_TOKEN);

        int rowsAffected = query.execute(sessionToken, id);

        if (rowsAffected != 1)
        {
            throw new RuntimeException();
        }
    }

    public static Account create(String email, String nickname, String password, String sessionToken) throws AccountAlreadyExistsException, DatabaseException
    {
        InsertQuery query = new InsertQuery(AccountQueries.INSERT);

        try
        {
            long id = query.execute(
                    email,
                    nickname,
                    SHA.sha512(password),
                    sessionToken
            );

            return new Account(id, email, nickname);
        }
        catch (DatabaseException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new AccountAlreadyExistsException(e);
        }
    }
}