package com.mauriciotogneri.momowars.test.suites.player;

import com.mauriciotogneri.momowars.test.suites.match.JoinMatchTests;

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