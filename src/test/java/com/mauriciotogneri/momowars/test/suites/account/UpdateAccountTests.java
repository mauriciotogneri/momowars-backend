package com.mauriciotogneri.momowars.test.suites.account;

import com.mauriciotogneri.momowars.api.endpoints.accounts.UpdateAccount;
import com.mauriciotogneri.momowars.api.model.Account;
import com.mauriciotogneri.momowars.test.suites.BaseTest;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UpdateAccountTests extends BaseTest implements UpdateAccount
{
    @Test
    public void test1InvalidSession() throws Exception
    {
        TestAccount account = testAccountLogged();
        accountService.updateAccount(UNAUTHORIZED, INVALID_SESSION, account.password, account.nickname);
    }

    @Test
    public void test2InvalidParameters() throws Exception
    {
        TestAccount account = testAccountLogged();
        accountService.updateAccount(BAD_REQUEST, account.session, null, null);
    }

    @Test
    public void test3Valid() throws Exception
    {
        TestAccount testAccount = testAccountLogged();

        Account account = accountService.updateAccount(OK, testAccount.session, testAccount.password, testAccount.nickname);
        Assert.assertNotEquals(null, account);
    }
}