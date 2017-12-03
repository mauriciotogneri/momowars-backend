package com.mauriciotogneri.momowars.test.endpoints.maps;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.path.PathParameters;
import com.mauriciotogneri.momowars.api.endpoints.maps.GetMap;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

public class GetMapEndPoint extends BaseEndPoint implements GetMap
{
    public GetMapEndPoint()
    {
        super(new EndPointDefinition(GetMap.class));
    }

    public ApiResult execute(String sessionToken, Long mapId) throws Exception
    {
        PathParameter path = new PathParameter();
        path.mapId = mapId;

        ApiRequest.Builder builder = request();
        builder.header(HEADER_SESSION_TOKEN, sessionToken);
        builder.path(new PathParameters(path), PATH_FORMAT);
        builder.response(jsonResponse());

        return process(builder);
    }
}