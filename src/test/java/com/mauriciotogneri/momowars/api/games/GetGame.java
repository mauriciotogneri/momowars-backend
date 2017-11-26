package com.mauriciotogneri.momowars.api.games;

import com.mauriciotogneri.momowars.api.games.GetGame.PathParameter;
import com.mauriciotogneri.momowars.model.games.Game;
import com.mauriciotogneri.momowars.model.headers.SessionToken;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;
import com.mauriciotogneri.stewie.types.MimeType;

import static com.mauriciotogneri.stewie.types.Method.GET;
import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.FORBIDDEN;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@EndPoint(
        path = "/v1/games/{gameId}",
        method = GET,
        description = "Returns a game by its id"
)
@Parameters(
        header = SessionToken.class,
        path = PathParameter.class
)
@Responses({
        @Response(
                code = OK,
                description = "Successful operation",
                produces = MimeType.JSON,
                type = Game.class
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
                code = FORBIDDEN,
                description = "Not allowed to retrieve the game"
        )
})
public interface GetGame
{
    class PathParameter
    {
        public Long gameId;
    }
}