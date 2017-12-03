package com.mauriciotogneri.momowars.test.services;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.test.endpoints.players.JoinGameEndPoint;

public class PlayerServiceTest extends BaseService
{
    private static final JoinGameEndPoint joinGameEndPoint = new JoinGameEndPoint();

    public void joinGame(int statusCode, String sessionToken, Long gameId) throws Exception
    {
        ApiResult result = joinGameEndPoint.execute(sessionToken, gameId);
        checkHttpStatus(statusCode, result);
    }
}