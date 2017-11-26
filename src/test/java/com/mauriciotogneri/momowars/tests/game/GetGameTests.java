package com.mauriciotogneri.momowars.tests.game;

import com.mauriciotogneri.momowars.tests.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetGameTests extends BaseTest
{
    @Test
    public void test1GetAGameWithAnInvalidSession() throws Exception
    {
        //ApiResult result = getGameEndPoint.execute("xxx", 0L);
        //checkHttpStatus(UNAUTHORIZED, result);
    }

    @Test
    public void test2GetAGameWithInvalidParameters() throws Exception
    {
        //ApiResult result = getGameEndPoint.execute("", 0L);
        //checkHttpStatus(BAD_REQUEST, result);
    }

    @Test
    public void test3GetAGameWithAValidSessionAndInvalidId() throws Exception
    {
        //ApiResult result = getGameEndPoint.execute("xxx", 0L);
        //checkHttpStatus(FORBIDDEN, result);
    }

    @Test
    public void test4GetAGameWithAValidSessionAndValidId() throws Exception
    {
        /*Long[] gameIds = new Long[0];

        for (Long gameId : gameIds)
        {
            ApiResult result = getGameEndPoint.execute("xx", gameId);
            checkHttpStatus(OK, result);

            Game game = json(result, Game.class);
            Assert.assertNotEquals(null, game);
        }*/
    }
}