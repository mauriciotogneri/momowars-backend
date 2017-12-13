package com.mauriciotogneri.momowars.api.endpoints.players;

import com.mauriciotogneri.momowars.api.endpoints.players.JoinGame.DataParameter;
import com.mauriciotogneri.momowars.api.model.games.Game;
import com.mauriciotogneri.momowars.api.model.headers.ApplicationJson;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;
import com.mauriciotogneri.stewie.types.MimeType;

import static com.mauriciotogneri.stewie.types.Method.POST;
import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;
import static com.mauriciotogneri.stewie.types.StatusCode.UNPROCESSABLE_ENTITY;

@EndPoint(
        path = "/v1/players",
        method = POST,
        description = "Joins the given game"
)
@Parameters(
        header = ApplicationJson.class,
        data = DataParameter.class
)
@Responses({
        @Response(
                code = CREATED,
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
                description = "Parameters missing or invalid"
        ),
        @Response(
                code = UNPROCESSABLE_ENTITY,
                description = "Either: the game doesn't exist" +
                        " or the player is already in the game" +
                        " or the game is playing" +
                        " or the game is finished."
        )
})
public interface JoinGame
{
    class DataParameter
    {
        public Long gameId;
    }
}