package com.mauriciotogneri.momowars.test.suites.map;

import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetMapsTests extends BaseTest
{
    @Test
    public void test1InvalidSession() throws Exception
    {
        mapService.getMaps(UNAUTHORIZED, INVALID_SESSION_TOKEN);
    }

    @Test
    public void test2Valid() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        mapService.getMaps(OK, testAccount.sessionToken);
    }
}