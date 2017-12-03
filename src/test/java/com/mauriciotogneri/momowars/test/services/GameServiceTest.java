package com.mauriciotogneri.momowars.test.services;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.api.model.games.Game;
import com.mauriciotogneri.momowars.test.endpoints.games.CreateGameEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.games.GetGameEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.games.GetOpenGamesEndPoint;

import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;

public class GameServiceTest extends BaseService
{
    private static final CreateGameEndPoint createGameEndPoint = new CreateGameEndPoint();
    private static final GetOpenGamesEndPoint getOpenGamesEndPoint = new GetOpenGamesEndPoint();
    private static final GetGameEndPoint getGameEndPoint = new GetGameEndPoint();

    public Game createGame(int statusCode, Long mapId, Integer maxPlayers, String sessionToken) throws Exception
    {
        ApiResult result = createGameEndPoint.execute(sessionToken, mapId, maxPlayers);
        checkHttpStatus(statusCode, result);

        return (statusCode == CREATED) ? json(result, Game.class) : null;
    }

    public Game[] getOpenGames(int statusCode, String sessionToken) throws Exception
    {
        ApiResult result = getOpenGamesEndPoint.execute(sessionToken);
        checkHttpStatus(statusCode, result);

        return (statusCode == OK) ? json(result, Game[].class) : new Game[0];
    }

    public Game getGame(int statusCode, Long gameId, String sessionToken) throws Exception
    {
        ApiResult result = getGameEndPoint.execute(sessionToken, gameId);
        checkHttpStatus(statusCode, result);

        return (statusCode == OK) ? json(result, Game.class) : null;
    }
}