package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.exceptions.InvalidMatchException;
import com.mauriciotogneri.momowars.exceptions.MatchFinishedException;
import com.mauriciotogneri.momowars.exceptions.MatchPlayingException;
import com.mauriciotogneri.momowars.exceptions.PlayerAlreadyJoinedException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.AccountMatches;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.model.Match;
import com.mauriciotogneri.momowars.repository.AccountDao;
import com.mauriciotogneri.momowars.repository.MapDao;
import com.mauriciotogneri.momowars.repository.MatchDao;
import com.mauriciotogneri.momowars.tasks.MatchStartedTask;
import com.mauriciotogneri.momowars.tasks.Task;
import com.mauriciotogneri.momowars.types.MatchStatus;

import java.util.List;

public class MatchService
{
    private final DatabaseConnection connection;

    public MatchService(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public Match createMatch(Integer maxPlayers,
                             Long mapId) throws DatabaseException, ApiException
    {
        MapDao mapDao = new MapDao(connection);
        Map map = mapDao.byId(mapId);

        MatchDao matchDao = new MatchDao(connection);

        return matchDao.create(maxPlayers, map);
    }

    public List<Match> getOpenMatches() throws DatabaseException
    {
        MatchDao matchDao = new MatchDao(connection);

        return matchDao.getOpenMatches();
    }

    public Match getMatch(Long matchId,
                          Long forAccountId) throws DatabaseException, ApiException
    {
        AccountDao accountDao = new AccountDao(connection);
        AccountMatches accountMatches = accountDao.accountMatches(forAccountId);

        if (!accountMatches.hasMatch(matchId))
        {
            throw new InvalidMatchException();
        }

        MatchDao matchDao = new MatchDao(connection);

        return matchDao.getMatch(matchId);
    }

    public Match joinMatch(Account account,
                           Long matchId) throws DatabaseException, ApiException
    {
        AccountDao accountDao = new AccountDao(connection);
        AccountMatches accountMatches = accountDao.accountMatches(account.id);

        if (accountMatches.hasMatch(matchId))
        {
            throw new PlayerAlreadyJoinedException();
        }

        MatchDao matchDao = new MatchDao(connection);

        Match match = matchDao.getMatch(matchId);

        if (match.isPlaying())
        {
            throw new MatchPlayingException();
        }
        else if (match.isFinished())
        {
            throw new MatchFinishedException();
        }

        PlayerService playerService = new PlayerService(connection);
        playerService.create(account.id, match.id());

        if (match.playersMissing() == 1)
        {
            startMatch(match.id());
        }

        return matchDao.getMatch(match.id());
    }

    public void startMatch(Long matchId) throws DatabaseException
    {
        MatchDao matchDao = new MatchDao(connection);
        matchDao.updateStatus(matchId, MatchStatus.PLAYING);

        Task task = new MatchStartedTask(matchId);
        task.submit();
    }
}