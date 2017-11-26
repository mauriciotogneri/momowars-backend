package com.mauriciotogneri.momowars.tests.game;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GetGameTests.class,
        GetOpenGamesTests.class,
        CreateGameTests.class
})
public class GameSuite
{
}