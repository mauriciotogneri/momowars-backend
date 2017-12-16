package com.mauriciotogneri.momowars.test.services;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.test.endpoints.players.EndTurnEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.players.JoinMatchEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.players.LeaveMatchEndPoint;

public class PlayerServiceTest extends BaseService
{
    private static final JoinMatchEndPoint joinMatchEndPoint = new JoinMatchEndPoint();
    private static final EndTurnEndPoint endTurnEndPoint = new EndTurnEndPoint();
    private static final LeaveMatchEndPoint leaveMatchEndPoint = new LeaveMatchEndPoint();

    public void joinMatch(int statusCode, String sessionToken, Long matchId) throws Exception
    {
        ApiResult result = joinMatchEndPoint.execute(sessionToken, matchId);
        checkHttpStatus(statusCode, result);
    }

    public void endTurn(int statusCode, String sessionToken, Long playerId) throws Exception
    {
        ApiResult result = endTurnEndPoint.execute(sessionToken, playerId);
        checkHttpStatus(statusCode, result);
    }

    public void leaveMatch(int statusCode, String sessionToken, Long playerId) throws Exception
    {
        ApiResult result = leaveMatchEndPoint.execute(sessionToken, playerId);
        checkHttpStatus(statusCode, result);
    }
}