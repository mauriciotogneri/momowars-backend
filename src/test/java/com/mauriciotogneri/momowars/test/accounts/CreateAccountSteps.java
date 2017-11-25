package com.mauriciotogneri.momowars.test.accounts;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.model.accounts.Account;
import com.mauriciotogneri.momowars.test.BaseSteps;

import org.junit.Assert;

import java.util.UUID;

import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.CONFLICT;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;

@StepDefAnnotation
public class CreateAccountSteps extends BaseSteps
{
    @When("^I create a new account with missing data$")
    public void createANewAccountWithMissingData() throws Exception
    {
        ApiResult result = createAccountEndPoint.execute(null, null, null);
        checkHttpStatus(BAD_REQUEST, result);
    }

    @When("^I create a new account with an existing email$")
    public void createANewAccountWithAnExistingEmail() throws Exception
    {
        String email = String.format("%s@email.com", System.currentTimeMillis());
        String password = UUID.randomUUID().toString();
        String nickname = "Momo";

        ApiResult result = createAccountEndPoint.execute(email, password, nickname);
        checkHttpStatus(CREATED, result);

        Account account = json(result, Account.class);
        Assert.assertEquals(email, account.email);
        Assert.assertEquals(nickname, account.nickname);
        Assert.assertEquals(new String[0], account.games);
    }

    @When("^I create a new account with valid data$")
    public void createANewAccountWithValidData() throws Exception
    {
        ApiResult result = createAccountEndPoint.execute("mauricio.togneri@gmail.com", "xxx", "Momo");
        checkHttpStatus(CONFLICT, result);
    }
}