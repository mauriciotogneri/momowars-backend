package com.mauriciotogneri.momowars.tests.game;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateGameTests.class,
        GetGameTests.class,
        GetOpenGamesTests.class
})
public class GameSuite
{
}