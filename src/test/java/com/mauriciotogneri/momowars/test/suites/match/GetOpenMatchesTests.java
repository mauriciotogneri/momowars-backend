package com.mauriciotogneri.momowars.test.suites.match;

import com.mauriciotogneri.momowars.api.endpoints.matches.GetOpenMatches;
import com.mauriciotogneri.momowars.api.model.Match;
import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetOpenMatchesTests extends BaseTest implements GetOpenMatches
{
    @Test
    public void test1InvalidSession() throws Exception
    {
        matchService.getOpenMatches(UNAUTHORIZED, INVALID_SESSION);
    }

    @Test
    public void test2Valid() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        Match[] matches = matchService.getOpenMatches(OK, testAccount.session);
        Assert.assertNotEquals(0, matches.length);
    }
}