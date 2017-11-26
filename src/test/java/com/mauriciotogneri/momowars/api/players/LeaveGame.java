package com.mauriciotogneri.momowars.api.players;

import com.mauriciotogneri.momowars.api.players.LeaveGame.PathParameter;
import com.mauriciotogneri.momowars.model.headers.SessionToken;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;

import static com.mauriciotogneri.stewie.types.Method.DELETE;
import static com.mauriciotogneri.stewie.types.StatusCode.FORBIDDEN;
import static com.mauriciotogneri.stewie.types.StatusCode.NO_CONTENT;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@EndPoint(
        path = "/v1/games/{gameId}/players/{playerId}",
        method = DELETE,
        description = "Leaves the given game"
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
                code = FORBIDDEN,
                description = "Not allowed to leave the given game"
        )
})
public interface LeaveGame
{
    class PathParameter
    {
        public Long gameId;

        public Long playerId;
    }
}