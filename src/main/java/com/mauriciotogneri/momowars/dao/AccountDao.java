package com.mauriciotogneri.momowars.dao;

import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.DatabaseException;
import com.mauriciotogneri.momowars.database.SQL.AccountQueries;
import com.mauriciotogneri.momowars.database.rows.AccountRow;
import com.mauriciotogneri.momowars.database.sql.InsertQuery;
import com.mauriciotogneri.momowars.database.sql.SelectQuery;
import com.mauriciotogneri.momowars.database.sql.UpdateQuery;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.exceptions.AccountAlreadyExistsException;
import com.mauriciotogneri.momowars.model.exceptions.AccountNotFoundException;
import com.mauriciotogneri.momowars.utils.SHA;

import java.sql.Connection;
import java.util.List;

public class AccountDao
{
    private final Connection connection;

    public AccountDao(DatabaseConnection connection)
    {
        this.connection = connection.connection();
    }

    public Account bySessionToken(String sessionToken) throws AccountNotFoundException, DatabaseException
    {
        SelectQuery<AccountRow> query = new SelectQuery<>(connection, AccountQueries.SELECT_BY_SESSION_TOKEN, AccountRow.class);
        List<AccountRow> result = query.execute(sessionToken);

        if (!result.isEmpty())
        {
            return result.get(0).account();
        }
        else
        {
            throw new AccountNotFoundException();
        }
    }

    public Account byCredentials(String email, String password) throws AccountNotFoundException, DatabaseException
    {
        SelectQuery<AccountRow> query = new SelectQuery<>(connection, AccountQueries.SELECT_BY_CREDENTIALS, AccountRow.class);

        List<AccountRow> result = query.execute(email, SHA.sha512(password));

        if (!result.isEmpty())
        {
            return result.get(0).account();
        }
        else
        {
            throw new AccountNotFoundException();
        }
    }

    public void updateSessionToken(Long id, String sessionToken) throws DatabaseException
    {
        UpdateQuery query = new UpdateQuery(connection, AccountQueries.UPDATE_SESSION_TOKEN);

        int rowsAffected = query.execute(sessionToken, id);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }

    public Account create(String email, String nickname, String password, String sessionToken) throws AccountAlreadyExistsException
    {
        InsertQuery query = new InsertQuery(connection, AccountQueries.INSERT);

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
            throw new AccountAlreadyExistsException(e);
        }
    }
}