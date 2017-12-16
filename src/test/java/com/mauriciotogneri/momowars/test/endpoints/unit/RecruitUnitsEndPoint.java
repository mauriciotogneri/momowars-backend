package com.mauriciotogneri.momowars.test.endpoints.unit;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.body.JsonBodyParameter;
import com.mauriciotogneri.apivalidator.parameters.path.PathParameters;
import com.mauriciotogneri.momowars.api.endpoints.units.RecruitUnits;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;
import com.mauriciotogneri.momowars.types.UnitType;

public class RecruitUnitsEndPoint extends BaseEndPoint implements RecruitUnits
{
    public RecruitUnitsEndPoint()
    {
        super(new EndPointDefinition(RecruitUnits.class));
    }

    public ApiResult execute(String session, Long matchId, Long playerId, Long cellId, UnitType type, Integer quantity) throws Exception
    {
        PathParameter path = new PathParameter();
        path.matchId = matchId;
        path.playerId = playerId;
        path.cellId = cellId;

        DataParameter data = new DataParameter();
        data.type = type;
        data.quantity = quantity;

        ApiRequest.Builder builder = request();
        builder.header(sessionCookie(session));
        builder.path(new PathParameters(path), PATH_FORMAT);
        builder.body(new JsonBodyParameter(data));
        builder.response(jsonResponse());

        return process(builder);
    }
}