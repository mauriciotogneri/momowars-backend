package com.mauriciotogneri.momowars.test.services;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.test.endpoints.match.JoinMatchEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.player.EndTurnEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.player.LeaveMatchEndPoint;

public class PlayerServiceTest extends BaseService
{
    private static final JoinMatchEndPoint joinMatchEndPoint = new JoinMatchEndPoint();
    private static final EndTurnEndPoint endTurnEndPoint = new EndTurnEndPoint();
    private static final LeaveMatchEndPoint leaveMatchEndPoint = new LeaveMatchEndPoint();

    public void joinMatch(int statusCode, String session, Long matchId) throws Exception
    {
        ApiResult result = joinMatchEndPoint.execute(session, matchId);
        checkHttpStatus(statusCode, result);
    }

    public void endTurn(int statusCode, String session, Long playerId) throws Exception
    {
        ApiResult result = endTurnEndPoint.execute(session, playerId);
        checkHttpStatus(statusCode, result);
    }

    public void leaveMatch(int statusCode, String session, Long playerId) throws Exception
    {
        ApiResult result = leaveMatchEndPoint.execute(session, playerId);
        checkHttpStatus(statusCode, result);
    }
}