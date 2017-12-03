package com.mauriciotogneri.momowars.test.suites.player;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        JoinGameTests.class,
        EndTurnTests.class,
        LeaveGameTests.class
})
public class PlayerSuite
{
}