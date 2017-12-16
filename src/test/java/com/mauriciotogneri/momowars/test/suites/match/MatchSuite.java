package com.mauriciotogneri.momowars.test.suites.match;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateMatchTests.class,
        GetMatchTests.class,
        GetOpenMatchesTests.class
})
public class MatchSuite
{
}