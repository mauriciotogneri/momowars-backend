package com.mauriciotogneri.momowars.test.services;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.api.model.Account;
import com.mauriciotogneri.momowars.test.endpoints.account.CreateAccountEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.account.GetAccountEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.account.UpdateAccountEndPoint;

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

    public Account getAccount(int statusCode, String session) throws Exception
    {
        ApiResult result = getAccountEndPoint.execute(session);
        checkHttpStatus(statusCode, result);

        return (statusCode == OK) ? json(result, Account.class) : null;
    }

    public Account updateAccount(int statusCode, String session, String password, String nickname) throws Exception
    {
        ApiResult result = updateAccountEndPoint.execute(session, password, nickname);
        checkHttpStatus(statusCode, result);

        return (statusCode == OK) ? json(result, Account.class) : null;
    }
}