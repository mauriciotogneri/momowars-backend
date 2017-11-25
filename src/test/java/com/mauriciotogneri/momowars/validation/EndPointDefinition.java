package com.mauriciotogneri.momowars.validation;

import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;

public class EndPointDefinition
{
    private final String path;
    private final String method;
    private final Integer code;
    private final Class<?> type;

    public EndPointDefinition(Class<?> clazz)
    {
        EndPoint endPoint = clazz.getAnnotation(EndPoint.class);
        Responses responses = clazz.getAnnotation(Responses.class);
        Response response = responses.value()[0];

        this.path = System.getenv("LOCAL_SERVER") + endPoint.path();
        this.method = endPoint.method();
        this.code = response.code();
        this.type = response.type();
    }

    public String path()
    {
        return path;
    }

    public String method()
    {
        return method;
    }

    public Integer code()
    {
        return code;
    }

    public Class<?> type()
    {
        return type;
    }
}