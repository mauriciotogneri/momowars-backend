package com.mauriciotogneri.momowars.tests.account;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateAccountTests.class,
        GetAccountSteps.class,
        UpdateAccountSteps.class,
})
public class AccountSuite
{
}