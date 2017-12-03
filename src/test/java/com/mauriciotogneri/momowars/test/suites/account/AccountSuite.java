package com.mauriciotogneri.momowars.test.suites.account;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateAccountTests.class,
        GetAccountTests.class,
        UpdateAccountTests.class,
})
public class AccountSuite
{
}