package com.mauriciotogneri.momowars.api.endpoints.matches;

import com.mauriciotogneri.momowars.api.endpoints.matches.EndTurn.PathParameter;
import com.mauriciotogneri.momowars.api.model.SessionCookie;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;

import static com.mauriciotogneri.stewie.types.Method.DELETE;
import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.NOT_FOUND;
import static com.mauriciotogneri.stewie.types.StatusCode.NO_CONTENT;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;
import static com.mauriciotogneri.stewie.types.StatusCode.UNPROCESSABLE_ENTITY;

@EndPoint(
        path = "/v1/matches/{matchId}/players/{playerId}/turn",
        method = DELETE,
        description = "Ends the turn of the player for the given match"
)
@Parameters(
        header = SessionCookie.class,
        path = PathParameter.class
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
                code = NOT_FOUND,
                description = "Player not found"
        ),
        @Response(
                code = UNPROCESSABLE_ENTITY,
                description = "Not allowed to end the turn on the given match"
        )
})
public interface EndTurn
{
    class PathParameter
    {
        public Long matchId;

        public Long playerId;
    }
}