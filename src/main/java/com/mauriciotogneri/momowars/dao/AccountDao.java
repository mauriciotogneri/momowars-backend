package com.mauriciotogneri.momowars.dao;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.QueryResult;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.inquiry.queries.UpdateQuery;
import com.mauriciotogneri.javautils.Encoding;
import com.mauriciotogneri.jerry.exceptions.server.InternalServerErrorException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.AccountQueries;
import com.mauriciotogneri.momowars.database.rows.AccountRow;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.exceptions.AccountAlreadyExistsException;
import com.mauriciotogneri.momowars.model.exceptions.AccountNotFoundException;

import java.security.NoSuchAlgorithmException;

public class AccountDao
{
    private final DatabaseConnection connection;

    public AccountDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public Account bySessionToken(String sessionToken) throws AccountNotFoundException, DatabaseException
    {
        SelectQuery<AccountRow> query = connection.selectQuery(AccountQueries.SELECT_BY_SESSION_TOKEN, AccountRow.class);
        QueryResult<AccountRow> result = query.execute(sessionToken);

        if (result.hasElements())
        {
            return result.first().account();
        }
        else
        {
            throw new AccountNotFoundException();
        }
    }

    public Account byCredentials(String email, String password) throws AccountNotFoundException, DatabaseException
    {
        try
        {
            SelectQuery<AccountRow> query = connection.selectQuery(AccountQueries.SELECT_BY_CREDENTIALS, AccountRow.class);

            QueryResult<AccountRow> result = query.execute(email, Encoding.sha512(password));

            if (result.hasElements())
            {
                return result.first().account();
            }
            else
            {
                throw new AccountNotFoundException();
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new InternalServerErrorException(e);
        }
    }

    public void updateSessionToken(Long id, String sessionToken) throws DatabaseException
    {
        UpdateQuery query = connection.updateQuery(AccountQueries.UPDATE_SESSION_TOKEN);

        int rowsAffected = query.execute(sessionToken, id);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }

    public Account create(String email, String nickname, String password, String sessionToken) throws AccountAlreadyExistsException
    {
        InsertQuery query = connection.insertQuery(AccountQueries.INSERT);

        try
        {
            long id = query.execute(
                    email,
                    nickname,
                    Encoding.sha512(password),
                    sessionToken
            );

            return new Account(id, email, nickname);
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new InternalServerErrorException(e);
        }
        catch (DatabaseException e)
        {
            throw new AccountAlreadyExistsException(e);
        }
    }
}