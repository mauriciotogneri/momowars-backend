package com.mauriciotogneri.momowars.api.endpoints.units;

import com.mauriciotogneri.momowars.api.endpoints.units.MoveUnits.DataParameter;
import com.mauriciotogneri.momowars.api.endpoints.units.MoveUnits.PathParameter;
import com.mauriciotogneri.momowars.api.model.SessionCookie;
import com.mauriciotogneri.momowars.types.MovementType;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;

import static com.mauriciotogneri.stewie.types.Method.PUT;
import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.NO_CONTENT;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;
import static com.mauriciotogneri.stewie.types.StatusCode.UNPROCESSABLE_ENTITY;

@EndPoint(
        path = "/v1/matches/{matchId}/players/{playerId}/units/{unitId}/move",
        method = PUT,
        description = "Enqueues the command to move the units"
)
@Parameters(
        header = SessionCookie.class,
        path = PathParameter.class,
        data = DataParameter.class
)
@Responses({
        @Response(
                code = NO_CONTENT,
                description = "Successful operation"
        ),
        @Response(
                code = UNAUTHORIZED,
                description = "Session not valid"
        ),
        @Response(
                code = BAD_REQUEST,
                description = "Parameters missing or invalid"
        ),
        @Response(
                code = UNPROCESSABLE_ENTITY,
                description = "Invalid matchId, cellId or unitId"
        )
})
public interface MoveUnits
{
    class PathParameter
    {
        public Long matchId;

        public Long playerId;

        public Long cellId;

        public Long unitId;
    }

    class DataParameter
    {
        public MovementType movement;
    }
}