package com.mauriciotogneri.momowars.test.services;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.api.model.Match;
import com.mauriciotogneri.momowars.test.endpoints.match.CreateMatchEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.match.GetMatchEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.match.GetOpenMatchesEndPoint;

import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;

public class MatchServiceTest extends BaseService
{
    private static final CreateMatchEndPoint createMatchEndPoint = new CreateMatchEndPoint();
    private static final GetOpenMatchesEndPoint getOpenMatchesEndPoint = new GetOpenMatchesEndPoint();
    private static final GetMatchEndPoint getMatchEndPoint = new GetMatchEndPoint();

    public Match createMatch(int statusCode, Long mapId, Integer maxPlayers, String sessionToken) throws Exception
    {
        ApiResult result = createMatchEndPoint.execute(sessionToken, mapId, maxPlayers);
        checkHttpStatus(statusCode, result);

        return (statusCode == CREATED) ? json(result, Match.class) : null;
    }

    public Match[] getOpenMatches(int statusCode, String sessionToken) throws Exception
    {
        ApiResult result = getOpenMatchesEndPoint.execute(sessionToken);
        checkHttpStatus(statusCode, result);

        return (statusCode == OK) ? json(result, Match[].class) : new Match[0];
    }

    public Match getMatch(int statusCode, Long matchId, String sessionToken) throws Exception
    {
        ApiResult result = getMatchEndPoint.execute(sessionToken, matchId);
        checkHttpStatus(statusCode, result);

        return (statusCode == OK) ? json(result, Match.class) : null;
    }
}