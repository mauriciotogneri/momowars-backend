package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.model.accounts.Account;
import com.mauriciotogneri.momowars.unit.BaseTest;
import com.mauriciotogneri.momowars.validation.endpoints.accounts.CreateAccountEndPoint;
import com.mauriciotogneri.momowars.validation.endpoints.accounts.GetAccountEndPoint;
import com.mauriciotogneri.momowars.validation.endpoints.accounts.UpdateAccountEndPoint;

import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;

public class AccountServiceTest extends BaseService
{
    private static final CreateAccountEndPoint createAccountEndPoint = new CreateAccountEndPoint();
    private static final GetAccountEndPoint getAccountEndPoint = new GetAccountEndPoint();
    private static final UpdateAccountEndPoint updateAccountEndPoint = new UpdateAccountEndPoint();

    public Account createAccount(int statusCode, String email, String password, String nickname) throws Exception
    {
        ApiResult result = createAccountEndPoint.execute(email, password, nickname);
        checkHttpStatus(statusCode, result);

        return (statusCode == CREATED) ? json(result, Account.class) : null;
    }

    public Account getAccount(int statusCode, String sessionToken) throws Exception
    {
        ApiResult result = getAccountEndPoint.execute(sessionToken);
        checkHttpStatus(statusCode, result);

        return (statusCode == OK) ? json(result, Account.class) : null;
    }

    public Account updateAccount(int statusCode, String sessionToken, String password, String nickname) throws Exception
    {
        ApiResult result = updateAccountEndPoint.execute(sessionToken, password, nickname);
        checkHttpStatus(statusCode, result);

        return (statusCode == OK) ? json(result, Account.class) : null;
    }

    public TestAccount testAccount() throws Exception
    {
        String email = BaseTest.randomEmail();
        String password = BaseTest.randomPassword();
        String nickname = BaseTest.randomNickname();

        Account account = createAccount(CREATED, email, password, nickname);

        return new TestAccount(account, password);
    }

    public TestAccount testAccountLogged() throws Exception
    {
        String email = BaseTest.randomEmail();
        String password = BaseTest.randomPassword();
        String nickname = BaseTest.randomNickname();

        Account account = createAccount(CREATED, email, password, nickname);

        String sessionToken = new SessionServiceTest().createSession(CREATED, email, password);

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