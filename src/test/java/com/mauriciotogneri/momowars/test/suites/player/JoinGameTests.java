package com.mauriciotogneri.momowars.test.suites.player;

import com.mauriciotogneri.momowars.api.endpoints.players.JoinGame;
import com.mauriciotogneri.momowars.api.model.games.Game;
import com.mauriciotogneri.momowars.api.model.games.Map;
import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;
import static com.mauriciotogneri.stewie.types.StatusCode.UNPROCESSABLE_ENTITY;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JoinGameTests extends BaseTest implements JoinGame
{
    @Test
    public void test1InvalidSession() throws Exception
    {
        playerService.joinGame(UNAUTHORIZED, INVALID_SESSION_TOKEN, 0L);
    }

    @Test
    public void test2InvalidGameIdParameter() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        playerService.joinGame(BAD_REQUEST, testAccount.sessionToken, null);
    }

    @Test
    public void test3GameDoesntExist() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        playerService.joinGame(UNPROCESSABLE_ENTITY, testAccount.sessionToken, 0L);
    }

    @Test
    public void test4PlayerAlreadyJoined() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        playerService.joinGame(UNPROCESSABLE_ENTITY, testAccount.sessionToken, 1L);
    }

    @Test
    public void test5GameIsPlaying() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        playerService.joinGame(UNPROCESSABLE_ENTITY, testAccount.sessionToken, 2L);
    }

    @Test
    public void test6GameIsFinished() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        playerService.joinGame(UNPROCESSABLE_ENTITY, testAccount.sessionToken, 3L);
    }

    @Test
    public void test8Valid() throws Exception
    {
        TestAccount testAccount1 = testAccountLogged();
        Map[] maps = mapService.getMaps(OK, testAccount1.sessionToken);
        Game game = gameService.createGame(CREATED, maps[0].id, 6, testAccount1.sessionToken);

        TestAccount testAccount2 = testAccountLogged();
        playerService.joinGame(CREATED, testAccount2.sessionToken, game.id);
    }
}