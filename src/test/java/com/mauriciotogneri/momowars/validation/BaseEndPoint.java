package com.mauriciotogneri.momowars.validation;

import com.mauriciotogneri.apivalidator.kernel.EndPoint;
import com.mauriciotogneri.apivalidator.kernel.Logger;
import com.mauriciotogneri.apivalidator.responses.EmptyExpectedResponse;
import com.mauriciotogneri.apivalidator.responses.ExpectedResponse;
import com.mauriciotogneri.apivalidator.responses.JsonExpectedResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.http2.Header;

public class BaseEndPoint extends EndPoint
{
    private final EndPointDefinition endPoint;

    private static OkHttpClient httpClient;
    private static Logger logger;

    protected static final Header CONTENT_TYPE_JSON = new Header("Content-Type", "application/json");
    public static final String HEADER_SESSION_TOKEN = "Session-Token";

    protected static final String PATH_FORMAT = "{%s}";

    public BaseEndPoint(EndPointDefinition endPoint)
    {
        super(httpClient(), logger(), endPoint.path(), endPoint.method());

        this.endPoint = endPoint;
    }

    protected ExpectedResponse emptyResponse()
    {
        return new EmptyExpectedResponse(endPoint.code());
    }

    protected ExpectedResponse jsonResponse()
    {
        return new JsonExpectedResponse(endPoint.code(), endPoint.type());
    }

    private static OkHttpClient httpClient()
    {
        if (httpClient == null)
        {
            httpClient = HttpClient.create();
        }

        return httpClient;
    }

    private static Logger logger()
    {
        if (logger == null)
        {
            try
            {
                logger = new Logger("logs");
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }

        return logger;
    }

    private static class HttpClient extends OkHttpClient
    {
        private static final int TIMEOUT = 20; // in seconds

        private HttpClient()
        {
        }

        public static OkHttpClient create()
        {
            return new Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .build();
        }
    }
}