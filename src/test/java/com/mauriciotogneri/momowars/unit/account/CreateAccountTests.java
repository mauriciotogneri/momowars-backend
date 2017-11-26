package com.mauriciotogneri.momowars.unit.account;

import com.mauriciotogneri.momowars.model.accounts.Account;
import com.mauriciotogneri.momowars.unit.BaseTest;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.CONFLICT;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateAccountTests extends BaseTest
{
    private static final String TEST_EMAIL = randomEmail();
    private static final String TEST_PASSWORD = randomPassword();
    private static final String TEST_NICKNAME = randomNickname();

    @Test
    public void test1CreateANewAccountWithMissingData() throws Exception
    {
        accountService.createAccount(BAD_REQUEST, null, null, null);
    }

    @Test
    public void test2CreateANewAccountWithAnExistingEmail() throws Exception
    {
        Account account = accountService.createAccount(CREATED, TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME);
        Assert.assertEquals(TEST_EMAIL, account.email);
        Assert.assertEquals(TEST_NICKNAME, account.nickname);
        Assert.assertArrayEquals(new Long[0], account.games);
    }

    @Test
    public void test3CreateANewAccountWithValidData() throws Exception
    {
        accountService.createAccount(CONFLICT, TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME);
    }
}