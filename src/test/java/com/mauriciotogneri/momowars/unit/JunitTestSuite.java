package com.mauriciotogneri.momowars.unit;

import com.mauriciotogneri.momowars.unit.account.AccountSuite;
import com.mauriciotogneri.momowars.unit.session.SessionSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountSuite.class,
        SessionSuite.class
})
public class JunitTestSuite
{
}