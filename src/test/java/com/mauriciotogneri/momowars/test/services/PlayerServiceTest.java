package com.mauriciotogneri.momowars.test.services;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.test.endpoints.players.EndTurnEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.players.JoinGameEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.players.LeaveGameEndPoint;

public class PlayerServiceTest extends BaseService
{
    private static final JoinGameEndPoint joinGameEndPoint = new JoinGameEndPoint();
    private static final EndTurnEndPoint endTurnEndPoint = new EndTurnEndPoint();
    private static final LeaveGameEndPoint leaveGameEndPoint = new LeaveGameEndPoint();

    public void joinGame(int statusCode, String sessionToken, Long gameId) throws Exception
    {
        ApiResult result = joinGameEndPoint.execute(sessionToken, gameId);
        checkHttpStatus(statusCode, result);
    }

    public void endTurn(int statusCode, String sessionToken, Long playerId) throws Exception
    {
        ApiResult result = endTurnEndPoint.execute(sessionToken, playerId);
        checkHttpStatus(statusCode, result);
    }

    public void leaveGame(int statusCode, String sessionToken, Long playerId) throws Exception
    {
        ApiResult result = leaveGameEndPoint.execute(sessionToken, playerId);
        checkHttpStatus(statusCode, result);
    }
}