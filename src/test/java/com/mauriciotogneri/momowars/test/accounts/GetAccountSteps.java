package com.mauriciotogneri.momowars.test.accounts;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.model.accounts.Account;
import com.mauriciotogneri.momowars.test.BaseSteps;

import org.junit.Assert;

import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;

@StepDefAnnotation
public class GetAccountSteps extends BaseSteps
{
    @When("^I get the account with an invalid session$")
    public void getAccountWithAnInvalidSession() throws Exception
    {
        ApiResult result = getAccountEndPoint.execute("xxx");
        checkHttpStatus(UNAUTHORIZED, result);
    }

    @When("^I get the account with a valid session$")
    public void getAccountWithAValidSession() throws Exception
    {
        ApiResult result = getAccountEndPoint.execute(SESSION_TOKEN);
        checkHttpStatus(OK, result);

        ACCOUNT = json(result, Account.class);
        Assert.assertEquals("mauricio.togneri@gmail.com", ACCOUNT.email);
        Assert.assertEquals("Momo", ACCOUNT.nickname);
    }
}