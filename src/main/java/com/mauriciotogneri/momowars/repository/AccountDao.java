package com.mauriciotogneri.momowars.repository;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.QueryResult;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.inquiry.queries.UpdateQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.AccountQueries;
import com.mauriciotogneri.momowars.exceptions.AccountAlreadyExistsException;
import com.mauriciotogneri.momowars.exceptions.AccountNotFoundException;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.exceptions.InvalidSessionException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.AccountMatches;
import com.mauriciotogneri.momowars.util.Hash;

import java.util.List;

public class AccountDao extends BaseDao
{
    public AccountDao(DatabaseConnection connection)
    {
        super(connection);
    }

    public Account byId(Long accountId) throws DatabaseException, ApiException
    {
        SelectQuery<Account> query = select(AccountQueries.SELECT_BY_ID, Account.class);
        QueryResult<Account> result = query.execute(accountId);

        if (result.hasElements())
        {
            return result.first();
        }
        else
        {
            throw new AccountNotFoundException();
        }
    }

    public Account bySession(String session) throws DatabaseException, ApiException
    {
        SelectQuery<Account> query = select(AccountQueries.SELECT_BY_SESSION, Account.class);
        QueryResult<Account> result = query.execute(session);

        if (result.hasElements())
        {
            return result.first();
        }
        else
        {
            throw new InvalidSessionException();
        }
    }

    public Account byEmail(String email) throws DatabaseException, ApiException
    {
        SelectQuery<Account> query = select(AccountQueries.SELECT_BY_EMAIL, Account.class);
        QueryResult<Account> result = query.execute(email);

        if (result.hasElements())
        {
            return result.first();
        }
        else
        {
            throw new AccountNotFoundException();
        }
    }

    public List<Account> byMatch(Long matchId) throws DatabaseException
    {
        SelectQuery<Account> query = select(AccountQueries.SELECT_BY_MATCH, Account.class);

        return query.execute(matchId);
    }

    public void updateSession(Long accountId, String session) throws DatabaseException
    {
        UpdateQuery query = update(AccountQueries.UPDATE_SESSION);

        int rowsAffected = query.execute(session, accountId);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }

    public void updatePassword(Long accountId, String password) throws DatabaseException
    {
        UpdateQuery query = update(AccountQueries.UPDATE_PASSWORD);

        int rowsAffected = query.execute(Hash.of(password), accountId);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }

    public void updateNickname(Long accountId, String nickname) throws DatabaseException
    {
        UpdateQuery query = update(AccountQueries.UPDATE_NICKNAME);

        int rowsAffected = query.execute(nickname, accountId);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }

    public void updatePicture(Long accountId, String picture) throws DatabaseException
    {
        UpdateQuery query = update(AccountQueries.UPDATE_PICTURE);

        int rowsAffected = query.execute(picture, accountId);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }

    public AccountMatches accountMatches(Long accountId) throws DatabaseException
    {
        SelectQuery<AccountMatches> query = select(AccountQueries.SELECT_MATCHES, AccountMatches.class);
        QueryResult<AccountMatches> result = query.execute(accountId);

        if (result.hasElements())
        {
            return result.first();
        }
        else
        {
            return new AccountMatches();
        }
    }

    public Account create(String email, String nickname, String session) throws ApiException
    {
        InsertQuery query = insert(AccountQueries.CREATE);

        try
        {
            long accountId = query.execute(email,
                                           nickname,
                                           session);

            return byId(accountId);
        }
        catch (DatabaseException e)
        {
            throw new AccountAlreadyExistsException(e);
        }
    }
}