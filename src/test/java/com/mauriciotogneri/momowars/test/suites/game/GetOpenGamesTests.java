package com.mauriciotogneri.momowars.test.suites.game;

import com.mauriciotogneri.momowars.api.model.games.Game;
import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetOpenGamesTests extends BaseTest
{
    @Test
    public void test1InvalidSession() throws Exception
    {
        gameService.getOpenGames(UNAUTHORIZED, INVALID_SESSION_TOKEN);
    }

    @Test
    public void test2Valid() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        Game[] games = gameService.getOpenGames(OK, testAccount.sessionToken);
        Assert.assertNotEquals(0, games.length);
    }
}