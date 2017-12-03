package com.mauriciotogneri.momowars.test.suites.player;

import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LeaveGameTests extends BaseTest
{
    @Test
    public void test1InvalidSession() throws Exception
    {
        playerService.leaveGame(UNAUTHORIZED, INVALID_SESSION_TOKEN, 0L);
    }

    @Test
    public void test2InvalidGameId() throws Exception
    {
        // TODO
        /*TestAccount testAccount = testAccountLogged();

        playerService.leaveGame(FORBIDDEN, testAccount.sessionToken, 0L);*/
    }

    @Test
    public void test3Valid() throws Exception
    {
        // TODO
    }
}