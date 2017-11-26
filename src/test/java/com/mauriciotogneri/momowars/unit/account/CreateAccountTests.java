package com.mauriciotogneri.momowars.unit.account;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.model.accounts.Account;
import com.mauriciotogneri.momowars.unit.BaseTest;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.UUID;

import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.CONFLICT;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateAccountTests extends BaseTest
{
    @Test
    public void test1CreateANewAccountWithMissingData() throws Exception
    {
        ApiResult result = createAccountEndPoint.execute(null, null, null);
        checkHttpStatus(BAD_REQUEST, result);
    }

    @Test
    public void test2CreateANewAccountWithAnExistingEmail() throws Exception
    {
        String email = String.format("%s@email.com", System.currentTimeMillis());
        String password = UUID.randomUUID().toString();
        String nickname = "Momo";

        ApiResult result = createAccountEndPoint.execute(email, password, nickname);
        checkHttpStatus(CREATED, result);

        Account account = json(result, Account.class);
        Assert.assertEquals(email, account.email);
        Assert.assertEquals(nickname, account.nickname);
        Assert.assertArrayEquals(new Long[0], account.games);
    }

    @Test
    public void test3CreateANewAccountWithValidData() throws Exception
    {
        ApiResult result = createAccountEndPoint.execute("mauricio.togneri@gmail.com", "xxx", "Momo");
        checkHttpStatus(CONFLICT, result);
    }
}