package com.mauriciotogneri.momowars.test.suites.game;

import com.mauriciotogneri.momowars.api.model.games.Map;
import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.NOT_FOUND;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateGameTests extends BaseTest
{
    @Test
    public void test1InvalidSession() throws Exception
    {
        gameService.createGame(UNAUTHORIZED, 0L, 2, INVALID_SESSION_TOKEN);
    }

    @Test
    public void test2InvalidMap() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        gameService.createGame(NOT_FOUND, 0L, 6, testAccount.sessionToken);
    }

    @Test
    public void test3InvalidPlayers() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        Map[] maps = mapService.getMaps(OK, testAccount.sessionToken);

        gameService.createGame(BAD_REQUEST, maps[0].id, 1, testAccount.sessionToken);
    }

    @Test
    public void test4Valid() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        Map[] maps = mapService.getMaps(OK, testAccount.sessionToken);

        gameService.createGame(CREATED, maps[0].id, 6, testAccount.sessionToken);
    }
}