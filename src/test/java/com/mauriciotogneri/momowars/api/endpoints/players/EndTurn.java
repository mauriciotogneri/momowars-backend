package com.mauriciotogneri.momowars.api.endpoints.players;

import com.mauriciotogneri.momowars.api.endpoints.players.EndTurn.PathParameter;
import com.mauriciotogneri.momowars.api.model.headers.SessionToken;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;

import static com.mauriciotogneri.stewie.types.Method.DELETE;
import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.NO_CONTENT;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@EndPoint(
        path = "/v1/games/{gameId}/players/{playerId}/turn",
        method = DELETE,
        description = "Ends the turn of the player for the given game"
)
@Parameters(
        header = SessionToken.class,
        path = PathParameter.class
)
@Responses({
        @Response(
                code = NO_CONTENT,
                description = "Successful operation"
        ),
        @Response(
                code = UNAUTHORIZED,
                description = "Session token not valid"
        ),
        @Response(
                code = BAD_REQUEST,
                description = "Session token not provided"
        ),
        @Response(
                code = 412,
                description = "Not allowed to end the turn on the given game"
        )
})
public interface EndTurn
{
    class PathParameter
    {
        public Long gameId;

        public Long playerId;
    }
}