package com.mauriciotogneri.momowars.test.suites.account;

import com.mauriciotogneri.momowars.api.model.accounts.Account;
import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UpdateAccountTests extends BaseTest
{
    @Test
    public void test1UpdateAccountWithAnInvalidSession() throws Exception
    {
        TestAccount account = testAccountLogged();
        accountService.updateAccount(UNAUTHORIZED, INVALID_SESSION_TOKEN, account.password, account.nickname);
    }

    @Test
    public void test2UpdateAccountWithInvalidParameters() throws Exception
    {
        TestAccount account = testAccountLogged();
        accountService.updateAccount(BAD_REQUEST, account.sessionToken, null, null);
    }

    @Test
    public void test3UpdateAccountWithValidParameters() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        Account account = accountService.updateAccount(OK, testAccount.sessionToken, testAccount.password, testAccount.nickname);
        Assert.assertNotEquals(null, account);
    }
}