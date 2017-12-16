package com.mauriciotogneri.momowars.test.suites.player;

import com.mauriciotogneri.momowars.api.endpoints.players.EndTurn;
import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.NOT_FOUND;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EndTurnTests extends BaseTest implements EndTurn
{
    @Test
    public void test1InvalidSession() throws Exception
    {
        playerService.endTurn(UNAUTHORIZED, INVALID_SESSION, 0L);
    }

    @Test
    public void test2InvalidPlayer() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        playerService.endTurn(NOT_FOUND, testAccount.session, 0L);
    }

    @Test
    public void test3WrongPlayer() throws Exception
    {
        // TODO
    }

    @Test
    public void test4Valid() throws Exception
    {
        // TODO
    }
}