package com.mauriciotogneri.momowars.tests.player;

import com.mauriciotogneri.momowars.tests.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EndTurnTests extends BaseTest
{
    @Test
    public void endATurnWithAnInvalidSession() throws Exception
    {
        //ApiResult result = endTurnEndPoint.execute("xxx", 0L, 0L);
        //checkHttpStatus(UNAUTHORIZED, result);
    }

    @Test
    public void endATurnWithInvalidParameters() throws Exception
    {
        //ApiResult result = endTurnEndPoint.execute("", 0L, 0L);
        //checkHttpStatus(BAD_REQUEST, result);
    }

    @Test
    public void endATurnOnAnInvalidGame() throws Exception
    {
        //ApiResult result = endTurnEndPoint.execute("xxx", 0L, 0L);
        //checkHttpStatus(412, result);
    }

    @Test
    public void endATurnOnAValidGame() throws Exception
    {
        /*Long[] gameIds = new Long[0];

        for (Long gameId : gameIds)
        {
            ApiResult result = endTurnEndPoint.execute("xxx", gameId, 0L);
            checkHttpStatus(NO_CONTENT, result);
        }*/
    }
}