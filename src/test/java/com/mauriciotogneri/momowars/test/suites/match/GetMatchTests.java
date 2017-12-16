package com.mauriciotogneri.momowars.test.suites.match;

import com.mauriciotogneri.momowars.api.endpoints.matches.GetMatch;
import com.mauriciotogneri.momowars.api.model.Map;
import com.mauriciotogneri.momowars.api.model.Match;
import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.FORBIDDEN;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetMatchTests extends BaseTest implements GetMatch
{
    @Test
    public void test1InvalidSession() throws Exception
    {
        matchService.getMatch(UNAUTHORIZED, 0L, INVALID_SESSION);
    }

    @Test
    public void test2NotAllowed() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        matchService.getMatch(FORBIDDEN, 0L, testAccount.session);
    }

    @Test
    public void test3Valid() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        Map[] maps = mapService.getMaps(OK, testAccount.session);

        Match match = matchService.createMatch(CREATED, maps[0].id, 6, testAccount.session);

        matchService.getMatch(OK, match.id, testAccount.session);
    }
}