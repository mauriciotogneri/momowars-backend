package com.mauriciotogneri.momowars;

import org.junit.Assert;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URI;

public class SuperTest extends BaseTest
{
    @Test
    public void superTest() throws Exception
    {
        URI serverUri = new URI("http://localhost:5000/api/session");
        HttpURLConnection http = (HttpURLConnection) serverUri.toURL().openConnection();
        http.connect();

        Assert.assertEquals(http.getResponseCode(), 404);
    }
}