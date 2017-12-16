package com.mauriciotogneri.momowars.test.suites.match;

import com.mauriciotogneri.momowars.api.endpoints.matches.JoinMatch;
import com.mauriciotogneri.momowars.api.model.Map;
import com.mauriciotogneri.momowars.api.model.Match;
import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.NOT_FOUND;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;
import static com.mauriciotogneri.stewie.types.StatusCode.UNPROCESSABLE_ENTITY;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JoinMatchTests extends BaseTest implements JoinMatch
{
    @Test
    public void test1InvalidSession() throws Exception
    {
        playerService.joinMatch(UNAUTHORIZED, INVALID_SESSION, 0L);
    }

    @Test
    public void test2InvalidMatchIdParameter() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        playerService.joinMatch(NOT_FOUND, testAccount.session, 0L);
    }

    @Test
    public void test3PlayerAlreadyJoined() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        playerService.joinMatch(UNPROCESSABLE_ENTITY, testAccount.session, 1L);
    }

    @Test
    public void test4MatchIsPlaying() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        playerService.joinMatch(UNPROCESSABLE_ENTITY, testAccount.session, 2L);
    }

    @Test
    public void test5MatchIsFinished() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        playerService.joinMatch(UNPROCESSABLE_ENTITY, testAccount.session, 3L);
    }

    @Test
    public void test6Valid() throws Exception
    {
        TestAccount testAccount1 = testAccountLogged();
        Map[] maps = mapService.getMaps(OK, testAccount1.session);
        Match match = matchService.createMatch(CREATED, maps[0].id, 6, testAccount1.session);

        TestAccount testAccount2 = testAccountLogged();
        playerService.joinMatch(CREATED, testAccount2.session, match.id);
    }
}