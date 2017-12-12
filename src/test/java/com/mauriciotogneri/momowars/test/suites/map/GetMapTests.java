package com.mauriciotogneri.momowars.test.suites.map;

import com.mauriciotogneri.momowars.api.endpoints.maps.GetMap;
import com.mauriciotogneri.momowars.api.model.games.Map;
import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.NOT_FOUND;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetMapTests extends BaseTest implements GetMap
{
    @Test
    public void test1InvalidSession() throws Exception
    {
        mapService.getMap(UNAUTHORIZED, INVALID_SESSION_TOKEN, 0L);
    }

    @Test
    public void test2InvalidParameters() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        mapService.getMap(NOT_FOUND, testAccount.sessionToken, 0L);
    }

    @Test
    public void test3Valid() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        Map[] maps = mapService.getMaps(OK, testAccount.sessionToken);

        for (Map map : maps)
        {
            mapService.getMap(OK, testAccount.sessionToken, map.id);
        }
    }
}