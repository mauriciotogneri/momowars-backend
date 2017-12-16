package com.mauriciotogneri.momowars.test.suites.player;

import com.mauriciotogneri.momowars.api.endpoints.players.LeaveMatch;
import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.NOT_FOUND;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LeaveMatchTests extends BaseTest implements LeaveMatch
{
    @Test
    public void test1InvalidSession() throws Exception
    {
        playerService.leaveMatch(UNAUTHORIZED, INVALID_SESSION_TOKEN, 0L);
    }

    @Test
    public void test2InvalidPlayer() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        playerService.leaveMatch(NOT_FOUND, testAccount.sessionToken, 0L);
    }

    @Test
    public void test3MatchAlreadyFinished() throws Exception
    {
        // TODO
        /*TestAccount testAccount = testAccountLogged();

        playerService.leaveMatch(UNPROCESSABLE_ENTITY, testAccount.sessionToken, 3L);*/
    }

    @Test
    public void test4Valid() throws Exception
    {
        // TODO
        /*TestAccount testAccount = testAccountLogged();

        playerService.leaveMatch(NO_CONTENT, testAccount.sessionToken, 1L);*/
    }
}