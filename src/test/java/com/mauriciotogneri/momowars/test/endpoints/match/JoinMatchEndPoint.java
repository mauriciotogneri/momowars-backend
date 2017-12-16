package com.mauriciotogneri.momowars.test.endpoints.match;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.path.PathParameters;
import com.mauriciotogneri.momowars.api.endpoints.matches.JoinMatch;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

import okhttp3.RequestBody;

public class JoinMatchEndPoint extends BaseEndPoint implements JoinMatch
{
    public JoinMatchEndPoint()
    {
        super(new EndPointDefinition(JoinMatch.class));
    }

    public ApiResult execute(String sessionToken, Long matchId) throws Exception
    {
        PathParameter path = new PathParameter();
        path.matchId = matchId;

        ApiRequest.Builder builder = request();
        builder.header(HEADER_SESSION_TOKEN, sessionToken);
        builder.path(new PathParameters(path), PATH_FORMAT);
        builder.body(RequestBody.create(null, new byte[] {}));
        builder.response(jsonResponse());

        return process(builder);
    }
}