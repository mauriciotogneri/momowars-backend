package com.mauriciotogneri.momowars.repository.match;

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
import com.mauriciotogneri.momowars.repository.map.MapDao;
import com.mauriciotogneri.momowars.repository.player.PlayerDao;
import com.mauriciotogneri.momowars.types.MatchStatus;

import java.util.ArrayList;
import java.util.List;

public class MatchDao
{
    private final DatabaseConnection connection;

    public MatchDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public Match create(Integer maxPlayers, Map map, Long forAccountId) throws DatabaseException, ApiException
    {
        InsertQuery query = connection.insertQuery(MatchQueries.CREATE);

        long matchId = query.execute(
                maxPlayers,
                map.id()
        );

        return getMatch(matchId, forAccountId);
    }

    public List<Match> getOpenMatches() throws DatabaseException, ApiException
    {
        MapDao mapDao = new MapDao(connection);
        PlayerDao playerDao = new PlayerDao(connection);

        SelectQuery<MatchRow> query = connection.selectQuery(MatchQueries.SELECT_OPEN, MatchRow.class);
        QueryResult<MatchRow> result = query.execute();

        List<Match> matches = new ArrayList<>();

        for (MatchRow match : result)
        {
            matches.add(match.match(
                    mapDao.getMap(match.mapId),
                    playerDao.getPlayers(match.id, 0L)
            ));
        }

        return matches;
    }

    public Match getMatch(Long matchId, Long forAccountId) throws DatabaseException, ApiException
    {
        MapDao mapDao = new MapDao(connection);
        PlayerDao playerDao = new PlayerDao(connection);

        SelectQuery<MatchRow> query = connection.selectQuery(MatchQueries.SELECT_BY_ID, MatchRow.class);
        QueryResult<MatchRow> result = query.execute(matchId);

        if (result.hasElements())
        {
            MatchRow row = result.first();

            return row.match(
                    mapDao.getMapFull(row.mapId),
                    playerDao.getPlayers(row.id, forAccountId)
            );
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

        List<Player> players = playerDao.getPlayers(matchId, null);

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