package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.model.games.Map;
import com.mauriciotogneri.momowars.validation.endpoints.maps.GetMapEndPoint;
import com.mauriciotogneri.momowars.validation.endpoints.maps.GetMapsEndPoint;

import static com.mauriciotogneri.stewie.types.StatusCode.OK;

public class MapServiceTest extends BaseService
{
    private static final GetMapsEndPoint getMapsEndPoint = new GetMapsEndPoint();
    private static final GetMapEndPoint getMapEndPoint = new GetMapEndPoint();

    public Map[] getMaps(int statusCode, String sessionToken) throws Exception
    {
        ApiResult result = getMapsEndPoint.execute(sessionToken);
        checkHttpStatus(statusCode, result);

        return (statusCode == OK) ? json(result, Map[].class) : new Map[0];
    }

    public Map getMap(int statusCode, String sessionToken, Long mapId) throws Exception
    {
        ApiResult result = getMapEndPoint.execute(sessionToken, mapId);
        checkHttpStatus(statusCode, result);

        return (statusCode == OK) ? json(result, Map.class) : null;
    }
}