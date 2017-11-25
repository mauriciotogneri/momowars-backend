package com.mauriciotogneri.momowars.test.session;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.test.BaseSteps;
import com.mauriciotogneri.momowars.validation.BaseEndPoint;

import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.NOT_FOUND;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;

@StepDefAnnotation
public class CreateSessionSteps extends BaseSteps
{
    @When("^I login with no parameters$")
    public void loginWithNoParameters() throws Exception
    {
        ApiResult result = createSessionEndPoint.execute(null, null);
        checkHttpStatus(BAD_REQUEST, result);
    }

    @When("^I login with an invalid email$")
    public void loginWithAnInvalidEmail() throws Exception
    {
        ApiResult result = createSessionEndPoint.execute("name@email.com", "password");
        checkHttpStatus(NOT_FOUND, result);
    }

    @When("^I login with an invalid password$")
    public void loginWithAnInvalidPassword() throws Exception
    {
        ApiResult result = createSessionEndPoint.execute("mauricio.togneri@gmail.com", "1234");
        checkHttpStatus(UNAUTHORIZED, result);
    }

    @When("^I login with valid credentials$")
    public void loginWithValidCredentials() throws Exception
    {
        ApiResult result = createSessionEndPoint.execute("mauricio.togneri@gmail.com", "password");
        checkHttpStatus(CREATED, result);
        SESSION_TOKEN = result.response().header(BaseEndPoint.HEADER_SESSION_TOKEN);
    }
}