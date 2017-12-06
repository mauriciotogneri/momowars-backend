package com.mauriciotogneri.momowars.test.suites.session;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateSessionEmailTests.class,
        CreateSessionGoogleTests.class
})
public class SessionSuite
{
}