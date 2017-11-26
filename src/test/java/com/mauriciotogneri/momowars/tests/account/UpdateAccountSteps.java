package com.mauriciotogneri.momowars.tests.account;

import com.mauriciotogneri.momowars.tests.BaseTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UpdateAccountSteps extends BaseTest
{
    @Test
    public void test1UpdateAccountWithAnInvalidSession() throws Exception
    {
        /*String email = randomEmail();
        String password = randomPassword();
        String nickname = randomNickname();

        accountService.createAccount(CREATED, email, password, nickname);
        accountService.updateAccount(UNAUTHORIZED, "xxx", password + "x", nickname + "x");*/
    }

    @Test
    public void test2UpdateAccountWithInvalidParameters() throws Exception
    {
        /*String email = randomEmail();
        String password = randomPassword();
        String nickname = randomNickname();

        accountService.createAccount(CREATED, email, password, nickname);
        String sessionToken = sessionService.createSession(CREATED, email, password);
        accountService.updateAccount(BAD_REQUEST, sessionToken, null, null);*/
    }

    @Test
    public void test3UpdateAccountWithValidParameters() throws Exception
    {
        /*String email = randomEmail();
        String password = randomPassword();
        String nickname = randomNickname();

        accountService.createAccount(CREATED, email, password, nickname);
        String sessionToken = sessionService.createSession(CREATED, email, password);

        Account account = accountService.updateAccount(OK, sessionToken, password + "x", nickname + "x");
        Assert.assertNotEquals(null, account);*/
    }
}