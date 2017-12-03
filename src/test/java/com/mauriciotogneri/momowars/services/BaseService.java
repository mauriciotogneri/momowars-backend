package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.javautils.Json;

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

    protected void empty(ApiResult apiResult)
    {
        if (!apiResult.isValid())
        {
            throw new RuntimeException(apiResult.error());
        }
    }

    protected void checkHttpStatus(int expected, ApiResult apiResult)
    {
        Assert.assertEquals(expected, apiResult.code());
    }
}