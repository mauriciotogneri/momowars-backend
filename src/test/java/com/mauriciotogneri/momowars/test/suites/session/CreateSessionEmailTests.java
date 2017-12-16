package com.mauriciotogneri.momowars.test.suites.session;

import com.mauriciotogneri.momowars.api.endpoints.sessions.CreateSessionEmail;
import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateSessionEmailTests extends BaseTest implements CreateSessionEmail
{
    @Test
    public void test1NoParameters() throws Exception
    {
        sessionService.createSessionEmail(BAD_REQUEST, null, null);
    }

    @Test
    public void test2NoEmail() throws Exception
    {
        sessionService.createSessionEmail(BAD_REQUEST, null, "xxx");
    }

    @Test
    public void test3NoPassword() throws Exception
    {
        sessionService.createSessionEmail(BAD_REQUEST, "email@email.com", null);
    }

    @Test
    public void test4InvalidEmail() throws Exception
    {
        TestAccount account = testAccount();

        sessionService.createSessionEmail(UNAUTHORIZED, "x" + account.email, account.password);
    }

    @Test
    public void test5InvalidPassword() throws Exception
    {
        TestAccount account = testAccount();

        sessionService.createSessionEmail(UNAUTHORIZED, account.email, "x" + account.password);
    }

    @Test
    public void test6Valid() throws Exception
    {
        TestAccount account = testAccount();

        String session = sessionService.createSessionEmail(CREATED, account.email, account.password);
        Assert.assertNotNull(session);
    }
}