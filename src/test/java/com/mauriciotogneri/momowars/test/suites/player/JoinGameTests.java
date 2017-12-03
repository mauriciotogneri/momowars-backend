package com.mauriciotogneri.momowars.test.suites.player;

import com.mauriciotogneri.momowars.api.model.games.Game;
import com.mauriciotogneri.momowars.api.model.games.Map;
import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.CONFLICT;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.NOT_FOUND;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JoinGameTests extends BaseTest
{
    @Test
    public void test1InvalidSession() throws Exception
    {
        playerService.joinGame(UNAUTHORIZED, INVALID_SESSION_TOKEN, 0L);
    }

    @Test
    public void test2InvalidGameId() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        playerService.joinGame(NOT_FOUND, testAccount.sessionToken, 0L);
    }

    @Test
    public void test3GameAlreadyJoined() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        playerService.joinGame(CONFLICT, testAccount.sessionToken, 1L);
    }

    @Test
    public void test4Valid() throws Exception
    {
        TestAccount testAccount1 = testAccountLogged();
        Map[] maps = mapService.getMaps(OK, testAccount1.sessionToken);
        Game game = gameService.createGame(CREATED, maps[0].id, 6, testAccount1.sessionToken);

        TestAccount testAccount2 = testAccountLogged();
        playerService.joinGame(CREATED, testAccount2.sessionToken, game.id);
    }
}