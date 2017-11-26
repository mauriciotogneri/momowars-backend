package com.mauriciotogneri.momowars.unit.session;

import com.mauriciotogneri.momowars.unit.BaseTest;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateSessionTests extends BaseTest
{
    @Test
    public void test1LoginWithNoParameters() throws Exception
    {
        sessionService.createSession(BAD_REQUEST, null, null);
    }

    @Test
    public void test2LoginWithAnInvalidEmail() throws Exception
    {
        TestAccount account = testAccount();

        sessionService.createSession(UNAUTHORIZED, "x" + account.email, account.password);
    }

    @Test
    public void test3LoginWithAnInvalidPassword() throws Exception
    {
        TestAccount account = testAccount();

        sessionService.createSession(UNAUTHORIZED, account.email, account.password + "x");
    }

    @Test
    public void test4LoginWithValidCredentials() throws Exception
    {
        TestAccount account = testAccount();

        String sessionToken = sessionService.createSession(CREATED, account.email, account.password);
        Assert.assertNotNull(sessionToken);
    }
}