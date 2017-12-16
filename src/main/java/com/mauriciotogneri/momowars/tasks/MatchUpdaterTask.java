package com.mauriciotogneri.momowars.tasks;

import com.mauriciotogneri.momowars.database.DatabaseConnection;

public class MatchUpdaterTask extends Task
{
    private final Long matchId;

    public MatchUpdaterTask(Long matchId)
    {
        this.matchId = matchId;
    }

    @Override
    public void process(DatabaseConnection connection) throws Exception
    {
        // TODO
        // create a table to store the before and after the update
        // id   match    before      after       duration

        //MatchDao matchDao = new MatchDao(connection);
        //matchDao.updateStatus(matchId, MatchStatus.PLAYING);
    }
}