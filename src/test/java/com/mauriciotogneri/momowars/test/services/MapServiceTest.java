package com.mauriciotogneri.momowars.test.services;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.api.model.Map;
import com.mauriciotogneri.momowars.test.endpoints.map.GetMapEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.map.GetMapsEndPoint;

import static com.mauriciotogneri.stewie.types.StatusCode.OK;

public class MapServiceTest extends BaseService
{
    private static final GetMapsEndPoint getMapsEndPoint = new GetMapsEndPoint();
    private static final GetMapEndPoint getMapEndPoint = new GetMapEndPoint();

    public Map[] getMaps(int statusCode, String session) throws Exception
    {
        ApiResult result = getMapsEndPoint.execute(session);
        checkHttpStatus(statusCode, result);

        return (statusCode == OK) ? json(result, Map[].class) : new Map[0];
    }

    public Map getMap(int statusCode, String session, Long mapId) throws Exception
    {
        ApiResult result = getMapEndPoint.execute(session, mapId);
        checkHttpStatus(statusCode, result);

        return (statusCode == OK) ? json(result, Map.class) : null;
    }
}