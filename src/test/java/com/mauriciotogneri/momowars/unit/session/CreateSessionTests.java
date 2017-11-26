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
        String email = randomEmail();
        String password = randomPassword();
        String nickname = randomNickname();

        accountService.createAccount(CREATED, email, password, nickname);
        sessionService.createSession(UNAUTHORIZED, "x" + email, password);
    }

    @Test
    public void test3LoginWithAnInvalidPassword() throws Exception
    {
        String email = randomEmail();
        String password = randomPassword();
        String nickname = randomNickname();

        accountService.createAccount(CREATED, email, password, nickname);
        sessionService.createSession(UNAUTHORIZED, email, password + "x");
    }

    @Test
    public void test4LoginWithValidCredentials() throws Exception
    {
        String email = randomEmail();
        String password = randomPassword();
        String nickname = randomNickname();

        accountService.createAccount(CREATED, email, password, nickname);

        String sessionToken = sessionService.createSession(CREATED, email, password);
        Assert.assertNotNull(sessionToken);
    }
}