package com.mauriciotogneri.momowars.repository.player;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.QueryResult;
import com.mauriciotogneri.inquiry.queries.DeleteQuery;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.inquiry.queries.UpdateQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.PlayerQueries;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.exceptions.MatchFinishedException;
import com.mauriciotogneri.momowars.exceptions.PlayerAlreadyLeftException;
import com.mauriciotogneri.momowars.exceptions.PlayerNotFoundException;
import com.mauriciotogneri.momowars.model.Constants;
import com.mauriciotogneri.momowars.model.Match;
import com.mauriciotogneri.momowars.model.Player;
import com.mauriciotogneri.momowars.repository.match.MatchDao;
import com.mauriciotogneri.momowars.repository.queue.QueueDao;
import com.mauriciotogneri.momowars.repository.unit.UnitDao;
import com.mauriciotogneri.momowars.tasks.MatchUpdaterTask;
import com.mauriciotogneri.momowars.tasks.Task;
import com.mauriciotogneri.momowars.types.PlayerStatus;

import java.util.ArrayList;
import java.util.List;

public class PlayerDao
{
    private final DatabaseConnection connection;

    public PlayerDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public void create(Long accountId, Long matchId) throws DatabaseException
    {
        InsertQuery createPlayerQuery = connection.insertQuery(PlayerQueries.CREATE);

        createPlayerQuery.execute(
                matchId,
                accountId,
                Constants.INITIAL_RESOURCES
        );
    }

    public List<Player> getPlayers(Long matchId, Long forAccountId) throws DatabaseException
    {
        SelectQuery<PlayerRow> query = connection.selectQuery(PlayerQueries.SELECT_BY_MATCH, PlayerRow.class);
        QueryResult<PlayerRow> result = query.execute(matchId);

        List<Player> players = new ArrayList<>();

        for (PlayerRow player : result)
        {
            players.add(player.player(forAccountId));
        }

        return players;
    }

    public PlayerRow getPlayer(Long playerId, Long accountId) throws DatabaseException, ApiException
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

            return row;
        }
        else
        {
            throw new PlayerNotFoundException();
        }
    }

    public void updateStatus(Long playerId, PlayerStatus status) throws DatabaseException
    {
        UpdateQuery updateQuery = connection.updateQuery(PlayerQueries.END_TURN);

        int rowsAffected = updateQuery.execute(status, playerId);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }

    public void endTurn(Long playerId, Long accountId) throws DatabaseException, ApiException
    {
        PlayerRow row = getPlayer(playerId, accountId);
        updateStatus(playerId, PlayerStatus.WAITING);

        MatchDao matchDao = new MatchDao(connection);

        if (matchDao.isUpdatable(row.matchId))
        {
            Task task = new MatchUpdaterTask(row.matchId);
            task.submit();
        }
    }

    public void leaveMatch(Long playerId, Long accountId) throws DatabaseException, ApiException
    {
        PlayerRow row = getPlayer(playerId, accountId);

        if ((row.status == PlayerStatus.VICTORIOUS) || (row.status == PlayerStatus.DEFEATED) || (row.status == PlayerStatus.SURRENDERED))
        {
            throw new PlayerAlreadyLeftException();
        }

        MatchDao matchDao = new MatchDao(connection);
        Match match = matchDao.getMatch(row.matchId, accountId);

        if (match.isFinished())
        {
            throw new MatchFinishedException();
        }
        else if (match.isPlaying())
        {
            updateStatus(playerId, PlayerStatus.SURRENDERED);

            UnitDao unitDao = new UnitDao(connection);
            unitDao.delete(playerId);

            QueueDao queueDao = new QueueDao(connection);
            queueDao.delete(playerId);

            if (matchDao.isUpdatable(row.matchId))
            {
                Task task = new MatchUpdaterTask(row.matchId);
                task.submit();
            }
        }
        else if (match.isOpen())
        {
            delete(playerId);

            if (match.playesJoined() == 1)
            {
                matchDao.delete(match.id());
            }
        }
    }

    public void delete(Long playerId) throws DatabaseException
    {
        DeleteQuery query = connection.deleteQuery(PlayerQueries.DELETE);

        int rowsAffected = query.execute(playerId);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }
}