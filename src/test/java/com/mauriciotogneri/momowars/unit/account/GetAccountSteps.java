package com.mauriciotogneri.momowars.unit.account;

import com.mauriciotogneri.momowars.model.accounts.Account;
import com.mauriciotogneri.momowars.unit.BaseTest;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetAccountSteps extends BaseTest
{
    @Test
    public void test1GetAccountWithAnInvalidSession() throws Exception
    {
        accountService.getAccount(UNAUTHORIZED, "xxx");
    }

    @Test
    public void test2GetAccountWithAValidSession() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        Account account = accountService.getAccount(OK, testAccount.sessionToken);
        Assert.assertEquals(testAccount.email, account.email);
        Assert.assertEquals(testAccount.nickname, account.nickname);
    }
}