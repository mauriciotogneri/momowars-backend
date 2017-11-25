package com.mauriciotogneri.momowars.test.games;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.model.games.Game;
import com.mauriciotogneri.momowars.test.BaseSteps;
import com.mauriciotogneri.momowars.test.session.CreateSessionSteps;

import org.junit.Assert;

import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.FORBIDDEN;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;

@StepDefAnnotation
public class GetGameSteps extends BaseSteps
{
    @When("^I get a game with an invalid session$")
    public void getAGameWithAnInvalidSession() throws Exception
    {
        ApiResult result = getGameEndPoint.execute("xxx", "xxx");
        checkHttpStatus(UNAUTHORIZED, result);
    }

    @When("^I get a game with invalid parameters$")
    public void getAGameWithInvalidParameters() throws Exception
    {
        ApiResult result = getGameEndPoint.execute("", "xxx");
        checkHttpStatus(BAD_REQUEST, result);
    }

    @When("^I get a game with a valid session and invalid id$")
    public void getAGameWithAValidSessionAndInvalidId() throws Exception
    {
        ApiResult result = getGameEndPoint.execute(CreateSessionSteps.SESSION_TOKEN, "xxx");
        checkHttpStatus(FORBIDDEN, result);
    }

    @When("^I get a game with a valid session and valid id$")
    public void getAGameWithAValidSessionAndValidId() throws Exception
    {
        for (String gameId : ACCOUNT.games)
        {
            ApiResult result = getGameEndPoint.execute(CreateSessionSteps.SESSION_TOKEN, gameId);
            checkHttpStatus(OK, result);

            Game game = json(result, Game.class);
            Assert.assertNotEquals(null, game);
        }
    }
}