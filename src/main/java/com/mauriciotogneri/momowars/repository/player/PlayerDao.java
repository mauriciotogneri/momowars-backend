package com.mauriciotogneri.momowars.repository.player;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.QueryResult;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.PlayerQueries;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.model.Constants;
import com.mauriciotogneri.momowars.model.Player;
import com.mauriciotogneri.momowars.repository.account.AccountDao;

import java.util.ArrayList;
import java.util.List;

public class PlayerDao
{
    private final DatabaseConnection connection;

    public PlayerDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public void create(Long accountId, Long gameId) throws DatabaseException
    {
        InsertQuery createPlayerQuery = connection.insertQuery(PlayerQueries.CREATE);

        long playerId = createPlayerQuery.execute(
                accountId,
                Constants.INITIAL_RESOURCES
        );

        InsertQuery joinGameQuery = connection.insertQuery(PlayerQueries.JOIN_GAME);

        joinGameQuery.execute(
                gameId,
                playerId
        );
    }

    public List<Player> getPlayers(Long gameId) throws DatabaseException, ApiException
    {
        AccountDao accountDao = new AccountDao(connection);

        SelectQuery<PlayerRow> query = connection.selectQuery(PlayerQueries.SELECT_BY_GAME, PlayerRow.class);
        QueryResult<PlayerRow> result = query.execute(gameId);

        List<Player> players = new ArrayList<>();

        for (PlayerRow player : result)
        {
            players.add(player.player(accountDao.getAccount(player.accountId)));
        }

        return players;
    }
}