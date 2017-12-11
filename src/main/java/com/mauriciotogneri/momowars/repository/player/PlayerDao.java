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
import com.mauriciotogneri.momowars.exceptions.GameFinishedException;
import com.mauriciotogneri.momowars.exceptions.PlayerNotFoundException;
import com.mauriciotogneri.momowars.model.Constants;
import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.model.Player;
import com.mauriciotogneri.momowars.repository.game.GameDao;
import com.mauriciotogneri.momowars.repository.queue.QueueDao;
import com.mauriciotogneri.momowars.repository.unit.UnitDao;
import com.mauriciotogneri.momowars.tasks.GameUpdaterTask;
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

    public void create(Long accountId, Long gameId) throws DatabaseException
    {
        InsertQuery createPlayerQuery = connection.insertQuery(PlayerQueries.CREATE);

        createPlayerQuery.execute(
                gameId,
                accountId,
                Constants.INITIAL_RESOURCES
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

        GameDao gameDao = new GameDao(connection);

        if (gameDao.isUpdatable(row.gameId))
        {
            Task task = new GameUpdaterTask(row.gameId);
            task.submit();
        }
    }

    public void leaveGame(Long playerId, Long accountId) throws DatabaseException, ApiException
    {
        PlayerRow row = getPlayer(playerId, accountId);

        GameDao gameDao = new GameDao(connection);
        Game game = gameDao.getGame(row.gameId, accountId);

        if (game.isFinished())
        {
            throw new GameFinishedException();
        }
        else if (game.isPlaying())
        {
            updateStatus(playerId, PlayerStatus.SURRENDERED);

            UnitDao unitDao = new UnitDao(connection);
            unitDao.delete(playerId);

            QueueDao queueDao = new QueueDao(connection);
            queueDao.delete(playerId);

            if (gameDao.isUpdatable(row.gameId))
            {
                Task task = new GameUpdaterTask(row.gameId);
                task.submit();
            }
        }
        else if (game.isOpen())
        {
            delete(playerId);

            if (game.playesJoined() == 1)
            {
                gameDao.delete(game.id());
            }
        }
    }

    public void delete(Long playerId) throws DatabaseException
    {
        DeleteQuery deleteQuery = connection.deleteQuery(PlayerQueries.DELETE);

        int rowsAffected = deleteQuery.execute(playerId);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }
}