package com.mauriciotogneri.momowars.tests;

import com.mauriciotogneri.momowars.model.accounts.Account;
import com.mauriciotogneri.momowars.services.AccountServiceTest;
import com.mauriciotogneri.momowars.services.MapServiceTest;
import com.mauriciotogneri.momowars.services.SessionServiceTest;

import java.util.UUID;

import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;

public class BaseTest
{
    protected final SessionServiceTest sessionService = new SessionServiceTest();
    protected final AccountServiceTest accountService = new AccountServiceTest();
    protected final MapServiceTest mapService = new MapServiceTest();

    public static String randomEmail()
    {
        return String.format("email_%s@email.com", UUID.randomUUID().toString().replace("-", ""));
    }

    public static String randomPassword()
    {
        return UUID.randomUUID().toString();
    }

    public static String randomNickname()
    {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TestAccount testAccount() throws Exception
    {
        String email = BaseTest.randomEmail();
        String password = BaseTest.randomPassword();
        String nickname = BaseTest.randomNickname();

        Account account = accountService.createAccount(CREATED, email, password, nickname);

        return new TestAccount(account, password);
    }

    public TestAccount testAccountLogged() throws Exception
    {
        String email = BaseTest.randomEmail();
        String password = BaseTest.randomPassword();
        String nickname = BaseTest.randomNickname();

        Account account = accountService.createAccount(CREATED, email, password, nickname);

        String sessionToken = sessionService.createSession(CREATED, email, password);

        return new TestAccount(account, password, sessionToken);
    }

    public static class TestAccount
    {
        public final Account account;
        public final String email;
        public final String password;
        public final String nickname;
        public final String sessionToken;

        public TestAccount(Account account, String password, String sessionToken)
        {
            this.account = account;
            this.email = account.email;
            this.password = password;
            this.nickname = account.nickname;
            this.sessionToken = sessionToken;
        }

        public TestAccount(Account account, String password)
        {
            this(account, password, null);
        }
    }
}