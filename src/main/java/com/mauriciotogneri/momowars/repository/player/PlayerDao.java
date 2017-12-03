package com.mauriciotogneri.momowars.repository.player;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.QueryResult;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.inquiry.queries.UpdateQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.PlayerQueries;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.exceptions.PlayerNotFoundException;
import com.mauriciotogneri.momowars.model.Constants;
import com.mauriciotogneri.momowars.model.Player;

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

    public List<Player> getPlayers(Long gameId, Long forAccountId) throws DatabaseException
    {
        SelectQuery<PlayerRow> query = connection.selectQuery(PlayerQueries.SELECT_BY_GAME, PlayerRow.class);
        QueryResult<PlayerRow> result = query.execute(gameId);

        List<Player> players = new ArrayList<>();

        for (PlayerRow player : result)
        {
            players.add(player.player(forAccountId));
        }

        return players;
    }

    public void endTurn(Long playerId, Long accountId) throws DatabaseException, ApiException
    {
        SelectQuery<PlayerRow> query = connection.selectQuery(PlayerQueries.SELECT_BY_ID, PlayerRow.class);
        QueryResult<PlayerRow> result = query.execute(playerId);

        if (result.hasElements())
        {
            PlayerRow row = result.first();

            if (!row.accountId.equals(accountId))
            {
                throw new PlayerNotFoundException();
            }

            UpdateQuery updateQuery = connection.updateQuery(PlayerQueries.UPDATE_TURN);

            int rowsAffected = updateQuery.execute(playerId);

            if (rowsAffected != 1)
            {
                throw new DatabaseException();
            }
        }
        else
        {
            throw new PlayerNotFoundException();
        }
    }

    public void leaveGame(Long playerId, Long accountId) throws DatabaseException, ApiException
    {
        // TODO
        // if game is still open =>
        //      remove player
        //      remove game from account
        // if game is running =>
        //      mark player as surrendered
        //      remove all units and queues
        //      remove game from account
        //      if he was the last one to move, update the game
    }
}