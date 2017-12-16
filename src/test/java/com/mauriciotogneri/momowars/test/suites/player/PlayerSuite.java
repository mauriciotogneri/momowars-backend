package com.mauriciotogneri.momowars.test.suites.player;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        JoinMatchTests.class,
        EndTurnTests.class,
        LeaveMatchTests.class
})
public class PlayerSuite
{
}