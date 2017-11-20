package com.mauriciotogneri.momowars.dao;

import com.mauriciotogneri.momowars.database.SQL.AccountQueries;
import com.mauriciotogneri.momowars.database.rows.AccountRow;
import com.mauriciotogneri.momowars.database.sql.InsertQuery;
import com.mauriciotogneri.momowars.database.sql.QueryResult;
import com.mauriciotogneri.momowars.database.sql.SelectQuery;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.exceptions.AccountExistsException;
import com.mauriciotogneri.momowars.model.exceptions.AccountNotFoundException;
import com.mauriciotogneri.momowars.utils.SHA;

public class AccountDao
{
    public static Account bySessionToken(String sessionToken) throws Exception
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

    public static Account byCredentials(String email, String password) throws Exception
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

    public static Account create(String email, String nickname, String password, String sessionToken) throws Exception
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
        catch (Exception e)
        {
            throw new AccountExistsException();
        }
    }
}