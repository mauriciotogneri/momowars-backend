package com.mauriciotogneri.momowars.repository;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.QueryResult;
import com.mauriciotogneri.inquiry.queries.DeleteQuery;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.inquiry.queries.UpdateQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.PlayerQueries;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.exceptions.InvalidMatchException;
import com.mauriciotogneri.momowars.exceptions.MatchFinishedException;
import com.mauriciotogneri.momowars.exceptions.PlayerAlreadyLeftException;
import com.mauriciotogneri.momowars.exceptions.PlayerNotFoundException;
import com.mauriciotogneri.momowars.model.Constants;
import com.mauriciotogneri.momowars.model.Match;
import com.mauriciotogneri.momowars.model.Player;
import com.mauriciotogneri.momowars.tasks.MatchUpdaterTask;
import com.mauriciotogneri.momowars.tasks.Task;
import com.mauriciotogneri.momowars.types.PlayerStatus;

import java.util.List;

public class PlayerDao extends BaseDao
{
    public PlayerDao(DatabaseConnection connection)
    {
        super(connection);
    }

    public void create(Long accountId, Long matchId) throws DatabaseException
    {
        InsertQuery createPlayerQuery = insert(PlayerQueries.CREATE);

        createPlayerQuery.execute(matchId,
                                  accountId,
                                  Constants.INITIAL_RESOURCES);
    }

    public List<Player> players(Long matchId) throws DatabaseException
    {
        SelectQuery<Player> query = select(PlayerQueries.SELECT_BY_MATCH, Player.class);

        return query.execute(matchId);
    }

    public Player byId(Long playerId) throws DatabaseException, ApiException
    {
        SelectQuery<Player> query = select(PlayerQueries.SELECT_BY_ID, Player.class);
        QueryResult<Player> result = query.execute(playerId);

        if (result.hasElements())
        {
            return result.first();
        }
        else
        {
            throw new PlayerNotFoundException();
        }
    }

    public void updateStatus(Long playerId, PlayerStatus status) throws DatabaseException
    {
        UpdateQuery updateQuery = update(PlayerQueries.END_TURN);

        int rowsAffected = updateQuery.execute(status, playerId);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }

    public void endTurn(Long matchId, Long playerId) throws DatabaseException, ApiException
    {
        Player player = byId(playerId);

        if (!player.matchId.equals(matchId))
        {
            throw new InvalidMatchException();
        }

        updateStatus(playerId, PlayerStatus.WAITING);

        MatchDao matchDao = new MatchDao(connection);

        if (matchDao.isUpdatable(player.matchId))
        {
            Task task = new MatchUpdaterTask(player.matchId);
            task.submit();
        }
    }

    public void leaveMatch(Long matchId, Long playerId) throws DatabaseException, ApiException
    {
        Player player = byId(playerId);

        if (!player.matchId.equals(matchId))
        {
            throw new InvalidMatchException();
        }

        if ((player.status == PlayerStatus.VICTORIOUS) || (player.status == PlayerStatus.DEFEATED) || (player.status == PlayerStatus.SURRENDERED))
        {
            throw new PlayerAlreadyLeftException();
        }

        MatchDao matchDao = new MatchDao(connection);
        Match match = matchDao.byId(player.matchId);

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

            if (matchDao.isUpdatable(player.matchId))
            {
                Task task = new MatchUpdaterTask(player.matchId);
                task.submit();
            }
        }
        else if (match.isOpen())
        {
            delete(playerId);

            if (match.playersJoined() == 1)
            {
                matchDao.delete(match.id());
            }
        }
    }

    public void delete(Long playerId) throws DatabaseException
    {
        DeleteQuery query = delete(PlayerQueries.DELETE);

        int rowsAffected = query.execute(playerId);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }
}