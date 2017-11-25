package com.mauriciotogneri.momowars.test.players;

import com.mauriciotogneri.momowars.test.BaseSteps;

import cucumber.runtime.java.StepDefAnnotation;

@StepDefAnnotation
public class EndTurnSteps extends BaseSteps
{
    /*@When("^I end a turn with an invalid session$")
    public void endATurnWithAnInvalidSession() throws Exception
    {
        ApiResult result = endTurnEndPoint.execute("xxx", "xxx", "xxx");
        checkHttpStatus(UNAUTHORIZED, result);
    }

    @When("^I end a turn with invalid parameters$")
    public void endATurnWithInvalidParameters() throws Exception
    {
        ApiResult result = endTurnEndPoint.execute("", "xxx", "xxx");
        checkHttpStatus(BAD_REQUEST, result);
    }

    @When("^I end a turn on an invalid game$")
    public void endATurnOnAnInvalidGame() throws Exception
    {
        ApiResult result = endTurnEndPoint.execute(CreateSessionSteps.SESSION_TOKEN, "xxx");
        checkHttpStatus(412, result);
    }

    @When("^I end a turn on a valid game$")
    public void endATurnOnAValidGame() throws Exception
    {
        for (String gameId : ACCOUNT.games)
        {
            ApiResult result = endTurnEndPoint.execute(CreateSessionSteps.SESSION_TOKEN, gameId);
            checkHttpStatus(NO_CONTENT, result);
        }
    }*/
}