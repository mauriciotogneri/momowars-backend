package com.mauriciotogneri.momowars.tests.map;

import com.mauriciotogneri.momowars.tests.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetMapsTests extends BaseTest
{
    @Test
    public void test1GetMapsWithAnInvalidSession() throws Exception
    {
        mapService.getMaps(UNAUTHORIZED, "xxx");
    }

    @Test
    public void test2GetMapsWithAValidSessionAndValidId() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        mapService.getMaps(OK, testAccount.sessionToken);
    }
}