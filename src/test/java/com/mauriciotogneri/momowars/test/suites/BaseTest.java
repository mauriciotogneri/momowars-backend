package com.mauriciotogneri.momowars.test.suites;

import com.mauriciotogneri.momowars.api.model.Account;
import com.mauriciotogneri.momowars.test.services.AccountServiceTest;
import com.mauriciotogneri.momowars.test.services.MapServiceTest;
import com.mauriciotogneri.momowars.test.services.MatchServiceTest;
import com.mauriciotogneri.momowars.test.services.PlayerServiceTest;
import com.mauriciotogneri.momowars.test.services.SessionServiceTest;

import java.util.UUID;

import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;

public class BaseTest
{
    protected final SessionServiceTest sessionService = new SessionServiceTest();
    protected final AccountServiceTest accountService = new AccountServiceTest();
    protected final MatchServiceTest matchService = new MatchServiceTest();
    protected final PlayerServiceTest playerService = new PlayerServiceTest();
    protected final MapServiceTest mapService = new MapServiceTest();

    protected static final String INVALID_SESSION = "invalid.session";

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

        String session = sessionService.createSessionEmail(CREATED, email, password);

        return new TestAccount(account, password, session);
    }

    public static class TestAccount
    {
        public final Account account;
        public final String email;
        public final String password;
        public final String nickname;
        public final String session;

        public TestAccount(Account account, String password, String session)
        {
            this.account = account;
            this.email = account.email;
            this.password = password;
            this.nickname = account.nickname;
            this.session = session;
        }

        public TestAccount(Account account, String password)
        {
            this(account, password, null);
        }
    }
}