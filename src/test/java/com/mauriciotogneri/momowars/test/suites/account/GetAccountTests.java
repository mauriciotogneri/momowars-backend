package com.mauriciotogneri.momowars.test.suites.account;

import com.mauriciotogneri.momowars.api.endpoints.accounts.GetAccount;
import com.mauriciotogneri.momowars.api.model.accounts.Account;
import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetAccountTests extends BaseTest implements GetAccount
{
    @Test
    public void test1InvalidSession() throws Exception
    {
        accountService.getAccount(UNAUTHORIZED, INVALID_SESSION_TOKEN);
    }

    @Test
    public void test2Valid() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        Account account = accountService.getAccount(OK, testAccount.sessionToken);
        Assert.assertEquals(testAccount.email, account.email);
        Assert.assertEquals(testAccount.nickname, account.nickname);
    }
}