package com.mauriciotogneri.momowars.repository.account;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.QueryResult;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.inquiry.queries.UpdateQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.AccountQueries;
import com.mauriciotogneri.momowars.exceptions.AccountAlreadyExistsException;
import com.mauriciotogneri.momowars.exceptions.AccountNotFoundException;
import com.mauriciotogneri.momowars.exceptions.InvalidCredentialsException;
import com.mauriciotogneri.momowars.exceptions.InvalidSessionTokenException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.util.Hash;

import java.util.ArrayList;
import java.util.List;

public class AccountDao
{
    private final DatabaseConnection connection;

    public AccountDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public Account bySessionToken(String sessionToken) throws InvalidSessionTokenException, DatabaseException
    {
        SelectQuery<AccountRow> query = connection.selectQuery(AccountQueries.SELECT_BY_SESSION_TOKEN, AccountRow.class);
        QueryResult<AccountRow> result = query.execute(sessionToken);

        if (result.hasElements())
        {
            AccountRow row = result.first();

            return row.account(accountGames(row.id));
        }
        else
        {
            throw new InvalidSessionTokenException();
        }
    }

    public Account byCredentials(String email, String password) throws InvalidCredentialsException, DatabaseException
    {
        SelectQuery<AccountRow> query = connection.selectQuery(AccountQueries.SELECT_BY_CREDENTIALS, AccountRow.class);

        QueryResult<AccountRow> result = query.execute(email, Hash.of(password));

        if (result.hasElements())
        {
            AccountRow row = result.first();

            return row.account(accountGames(row.id));
        }
        else
        {
            throw new InvalidCredentialsException();
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

    public void updatePassword(Long id, String password) throws DatabaseException
    {
        UpdateQuery query = connection.updateQuery(AccountQueries.UPDATE_PASSWORD);

        int rowsAffected = query.execute(Hash.of(password), id);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }

    public void updateNickname(Long id, String nickname) throws DatabaseException
    {
        UpdateQuery query = connection.updateQuery(AccountQueries.UPDATE_NICKNAME);

        int rowsAffected = query.execute(nickname, id);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }

    public Account byId(Long id) throws DatabaseException, AccountNotFoundException
    {
        SelectQuery<AccountRow> query = connection.selectQuery(AccountQueries.SELECT_BY_ID, AccountRow.class);

        QueryResult<AccountRow> result = query.execute(id);

        if (result.hasElements())
        {
            AccountRow row = result.first();

            return row.account(accountGames(row.id));
        }
        else
        {
            throw new AccountNotFoundException();
        }
    }

    public Account create(String email, String nickname, String password, String sessionToken) throws AccountAlreadyExistsException
    {
        InsertQuery query = connection.insertQuery(AccountQueries.CREATE);

        try
        {
            long id = query.execute(
                    email,
                    nickname,
                    Hash.of(password),
                    sessionToken
            );

            return new Account(id, email, nickname, new ArrayList<>());
        }
        catch (DatabaseException e)
        {
            throw new AccountAlreadyExistsException(e);
        }
    }

    public void joinGame(Long accountId, Long gameId) throws DatabaseException
    {
        InsertQuery query = connection.insertQuery(AccountQueries.JOIN_GAME);

        query.execute(
                accountId,
                gameId
        );
    }

    private List<Long> accountGames(Long id) throws DatabaseException
    {
        SelectQuery<AccountGamesRow> query = connection.selectQuery(AccountQueries.SELECT_GAMES, AccountGamesRow.class);
        QueryResult<AccountGamesRow> result = query.execute(id);

        List<Long> games = new ArrayList<>();

        for (AccountGamesRow row : result)
        {
            games.add(row.game);
        }

        return games;
    }
}