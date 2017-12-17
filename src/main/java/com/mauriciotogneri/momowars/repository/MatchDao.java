package com.mauriciotogneri.momowars.repository;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.QueryResult;
import com.mauriciotogneri.inquiry.queries.DeleteQuery;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.inquiry.queries.UpdateQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.MatchQueries;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.exceptions.MatchNotFoundException;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.model.Match;
import com.mauriciotogneri.momowars.model.Player;
import com.mauriciotogneri.momowars.types.MatchStatus;

import java.util.List;

public class MatchDao
{
    private final DatabaseConnection connection;

    public MatchDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public Match create(Integer maxPlayers, Map map) throws DatabaseException, ApiException
    {
        InsertQuery query = connection.insertQuery(MatchQueries.CREATE);

        long matchId = query.execute(
                maxPlayers,
                map.id
        );

        return getMatch(matchId);
    }

    public List<Match> getOpenMatches() throws DatabaseException
    {
        SelectQuery<Match> query = connection.selectQuery(MatchQueries.SELECT_OPEN, Match.class);

        return query.execute();
    }

    public Match getMatch(Long matchId) throws DatabaseException, ApiException
    {
        SelectQuery<Match> query = connection.selectQuery(MatchQueries.SELECT_BY_ID, Match.class);
        QueryResult<Match> result = query.execute(matchId);

        if (result.hasElements())
        {
            return result.first();
        }
        else
        {
            throw new MatchNotFoundException();
        }
    }

    public void updateStatus(Long matchId, MatchStatus status) throws DatabaseException
    {
        UpdateQuery query = connection.updateQuery(MatchQueries.UPDATE_STATUS);

        int rowsAffected = query.execute(status, matchId);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }

    public boolean isUpdatable(Long matchId) throws DatabaseException
    {
        PlayerDao playerDao = new PlayerDao(connection);

        List<Player> players = playerDao.getPlayers(matchId);

        for (Player player : players)
        {
            if (player.isPlaying())
            {
                return false;
            }
        }

        return true;
    }

    public void delete(Long playerId) throws DatabaseException
    {
        DeleteQuery query = connection.deleteQuery(MatchQueries.DELETE);

        int rowsAffected = query.execute(playerId);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }
}