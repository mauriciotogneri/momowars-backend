package com.mauriciotogneri.momowars.unit;

import com.mauriciotogneri.momowars.services.AccountServiceTest;
import com.mauriciotogneri.momowars.services.SessionServiceTest;

import java.util.UUID;

public class BaseTest
{
    protected final SessionServiceTest sessionService = new SessionServiceTest();
    protected final AccountServiceTest accountService = new AccountServiceTest();

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
}