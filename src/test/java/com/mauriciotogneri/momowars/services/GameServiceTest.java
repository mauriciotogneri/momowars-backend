package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.validation.endpoints.games.CreateGameEndPoint;

public class GameServiceTest extends BaseService
{
    private static final CreateGameEndPoint createGameEndPoint = new CreateGameEndPoint();

    public void createGame(int statusCode, Long mapId, Integer maxPlayers, String sessionToken) throws Exception
    {
        ApiResult result = createGameEndPoint.execute(sessionToken, mapId, maxPlayers);
        checkHttpStatus(statusCode, result);
    }
}