package com.mauriciotogneri.momowars.test.suites.game;

import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.NOT_FOUND;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateGameTests extends BaseTest
{
    @Test
    public void test1CreateGameWithAnInvalidSession() throws Exception
    {
        gameService.createGame(UNAUTHORIZED, 0L, 0, INVALID_SESSION_TOKEN);
    }

    @Test
    public void test2CreateGameWithInvalidMap() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        gameService.createGame(NOT_FOUND, 0L, 6, testAccount.sessionToken);
    }

    @Test
    public void test2CreateGameWithInvalidPlayers() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        gameService.createGame(BAD_REQUEST, 1L, 1, testAccount.sessionToken);
    }

    @Test
    public void test2CreateGameWithAValidSession() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        gameService.createGame(CREATED, 1L, 6, testAccount.sessionToken);
    }
}