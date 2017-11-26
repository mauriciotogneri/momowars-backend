package com.mauriciotogneri.momowars.tests.game;

import com.mauriciotogneri.momowars.tests.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetOpenGamesTests extends BaseTest
{
    @Test
    public void test1GetTheOpenGamesWithAnInvalidSession() throws Exception
    {
        //ApiResult result = getOpenGamesEndPoint.execute("xxx");
        //checkHttpStatus(UNAUTHORIZED, result);
    }

    @Test
    public void test2GetTheOpenGamesWithAValidSession() throws Exception
    {
        /*ApiResult result = getOpenGamesEndPoint.execute("xxx");
        checkHttpStatus(OK, result);

        Game[] games = json(result, Game[].class);
        Assert.assertNotEquals(null, games);*/
    }
}