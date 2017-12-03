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
import static com.mauriciotogneri.stewie.types.StatusCode.CONFLICT;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

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
                code = CONFLICT,
                description = "The player is already in the game. The game is not open. The game is full"
        )
})
public interface JoinGame
{
    class DataParameter
    {
        public Long gameId;
    }
}