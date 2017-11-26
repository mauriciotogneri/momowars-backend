package com.mauriciotogneri.momowars.unit;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.javautils.Json;
import com.mauriciotogneri.momowars.validation.endpoints.accounts.CreateAccountEndPoint;
import com.mauriciotogneri.momowars.validation.endpoints.accounts.GetAccountEndPoint;
import com.mauriciotogneri.momowars.validation.endpoints.accounts.UpdateAccountEndPoint;
import com.mauriciotogneri.momowars.validation.endpoints.games.GetGameEndPoint;
import com.mauriciotogneri.momowars.validation.endpoints.games.GetOpenGamesEndPoint;
import com.mauriciotogneri.momowars.validation.endpoints.players.EndTurnEndPoint;
import com.mauriciotogneri.momowars.validation.endpoints.session.CreateSessionEndPoint;

import org.junit.Assert;

public class BaseTest
{
    // SESSION
    protected final CreateSessionEndPoint createSessionEndPoint = new CreateSessionEndPoint();

    // ACCOUNT
    protected final CreateAccountEndPoint createAccountEndPoint = new CreateAccountEndPoint();
    protected final GetAccountEndPoint getAccountEndPoint = new GetAccountEndPoint();
    protected final UpdateAccountEndPoint updateAccountEndPoint = new UpdateAccountEndPoint();

    // GAMES
    protected final GetOpenGamesEndPoint getOpenGamesEndPoint = new GetOpenGamesEndPoint();
    protected final GetGameEndPoint getGameEndPoint = new GetGameEndPoint();
    protected final EndTurnEndPoint endTurnEndPoint = new EndTurnEndPoint();

    @SuppressWarnings("unchecked")
    protected <T> T json(ApiResult apiResult, Class<T> clazz) throws Exception
    {
        if (apiResult.isValid())
        {
            T result = Json.object(apiResult.string(), clazz);

            if (result == null)
            {
                throw new RuntimeException("Received empty response");
            }

            return result;
        }
        else
        {
            throw new RuntimeException(apiResult.error());
        }
    }

    protected void checkHttpStatus(int expected, ApiResult apiResult)
    {
        Assert.assertEquals(expected, apiResult.code());
    }
}