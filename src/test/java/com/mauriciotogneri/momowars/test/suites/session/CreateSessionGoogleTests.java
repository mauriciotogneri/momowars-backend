package com.mauriciotogneri.momowars.test.suites.session;

import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateSessionGoogleTests extends BaseTest
{
    @Test
    public void test1NoParameters() throws Exception
    {
        sessionService.createSessionGoogle(BAD_REQUEST, null);
    }

    @Test
    public void test2InvalidToken() throws Exception
    {
        sessionService.createSessionGoogle(UNAUTHORIZED, "xxx");
    }
}