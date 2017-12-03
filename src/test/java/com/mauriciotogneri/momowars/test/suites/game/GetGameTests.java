package com.mauriciotogneri.momowars.test.suites.game;

import com.mauriciotogneri.momowars.api.model.games.Game;
import com.mauriciotogneri.momowars.api.model.games.Map;
import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.FORBIDDEN;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetGameTests extends BaseTest
{
    @Test
    public void test1GetAGameWithAnInvalidSession() throws Exception
    {
        gameService.getGame(UNAUTHORIZED, 0L, INVALID_SESSION_TOKEN);
    }

    @Test
    public void test2GetAGameWithInvalidParameters() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        gameService.getGame(FORBIDDEN, 0L, testAccount.sessionToken);
    }

    @Test
    public void test3GetAGameWithAValidSession() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        Map[] maps = mapService.getMaps(OK, testAccount.sessionToken);

        Game game = gameService.createGame(CREATED, maps[0].id, 6, testAccount.sessionToken);

        gameService.getGame(OK, game.id, testAccount.sessionToken);
    }
}