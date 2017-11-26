package com.mauriciotogneri.momowars.unit;

import com.mauriciotogneri.momowars.services.AccountServiceTest;
import com.mauriciotogneri.momowars.services.SessionServiceTest;
import com.mauriciotogneri.momowars.validation.endpoints.games.GetGameEndPoint;
import com.mauriciotogneri.momowars.validation.endpoints.games.GetOpenGamesEndPoint;
import com.mauriciotogneri.momowars.validation.endpoints.players.EndTurnEndPoint;

import java.util.UUID;

public class BaseTest
{
    protected final SessionServiceTest sessionService = new SessionServiceTest();
    protected final AccountServiceTest accountService = new AccountServiceTest();

    // GAMES
    protected final GetOpenGamesEndPoint getOpenGamesEndPoint = new GetOpenGamesEndPoint();
    protected final GetGameEndPoint getGameEndPoint = new GetGameEndPoint();
    protected final EndTurnEndPoint endTurnEndPoint = new EndTurnEndPoint();

    protected static String randomEmail()
    {
        return String.format("email_%s@email.com", UUID.randomUUID().toString().replace("-", ""));
    }

    protected static String randomPassword()
    {
        return UUID.randomUUID().toString();
    }

    protected static String randomNickname()
    {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}