package com.mauriciotogneri.momowars.validation.endpoints.units;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.body.JsonBodyParameter;
import com.mauriciotogneri.apivalidator.parameters.path.PathParameters;
import com.mauriciotogneri.momowars.api.units.MoveUnits;
import com.mauriciotogneri.momowars.model.constants.MovementType;
import com.mauriciotogneri.momowars.validation.BaseEndPoint;
import com.mauriciotogneri.momowars.validation.EndPointDefinition;

public class MoveUnitsEndPoint extends BaseEndPoint implements MoveUnits
{
    public MoveUnitsEndPoint()
    {
        super(new EndPointDefinition(MoveUnits.class));
    }

    public ApiResult execute(String sessionToken, Long gameId, Long playerId, Long cellId, Long unitId, MovementType movement) throws Exception
    {
        PathParameter path = new PathParameter();
        path.gameId = gameId;
        path.playerId = playerId;
        path.cellId = cellId;
        path.unitId = unitId;

        DataParameter data = new DataParameter();
        data.movement = movement;

        ApiRequest.Builder builder = request();
        builder.header(HEADER_SESSION_TOKEN, sessionToken);
        builder.path(new PathParameters(path), PATH_FORMAT);
        builder.body(new JsonBodyParameter(data));
        builder.response(jsonResponse());

        return process(builder);
    }
}