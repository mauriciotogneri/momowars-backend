package com.mauriciotogneri.momowars.test.suites.session;

import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.NOT_FOUND;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateSessionEmailTests extends BaseTest
{
    @Test
    public void test1NoParameters() throws Exception
    {
        sessionService.createSessionEmail(BAD_REQUEST, null, null);
    }

    @Test
    public void test2InvalidEmail() throws Exception
    {
        TestAccount account = testAccount();

        sessionService.createSessionEmail(NOT_FOUND, "x" + account.email, account.password);
    }

    @Test
    public void test3InvalidPassword() throws Exception
    {
        TestAccount account = testAccount();

        sessionService.createSessionEmail(UNAUTHORIZED, account.email, account.password + "x");
    }

    @Test
    public void test4Valid() throws Exception
    {
        TestAccount account = testAccount();

        String sessionToken = sessionService.createSessionEmail(CREATED, account.email, account.password);
        Assert.assertNotNull(sessionToken);
    }
}