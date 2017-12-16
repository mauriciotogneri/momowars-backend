package com.mauriciotogneri.momowars.test.services;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.javautils.Json;
import com.mauriciotogneri.javautils.Strings;

import org.junit.Assert;

public class BaseService
{
    @SuppressWarnings("unchecked")
    protected <T> T json(ApiResult apiResult, Class<T> clazz)
    {
        if (apiResult.isValid())
        {
            T result = Json.object(apiResult.string(), clazz);

            if (result == null)
            {
                throw new RuntimeException("Received empty response");
            }

            return result;
        }
        else
        {
            throw new RuntimeException(apiResult.error());
        }
    }

    protected void checkHttpStatus(int expected, ApiResult apiResult)
    {
        Assert.assertEquals(expected, apiResult.code());
    }

    protected String sessionCookie(ApiResult result)
    {
        String cookies = result.response().header("Set-Cookie");

        if (cookies != null)
        {
            for (String cookie : cookies.split(";"))
            {
                String[] parts = cookie.split("=");

                if (parts.length == 2)
                {
                    if (Strings.equals(parts[0], "session"))
                    {
                        return parts[1];
                    }
                }
            }
        }

        return "";
    }
}