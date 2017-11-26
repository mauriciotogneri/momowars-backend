package com.mauriciotogneri.momowars.unit;

import org.junit.Assert;
import org.junit.Test;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;

public class SuperTestB extends BaseTest
{
    @Test
    public void superTest() throws Exception
    {
        URI serverUri = new URI("http://localhost:5000/api/v1/account");
        HttpURLConnection connection = (HttpURLConnection) serverUri.toURL().openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");

        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
        wr.write("{\"email\": \"mauricio.togneri@gmail.com\", \"password\": \"password\", \"nickname\": \"Momo\"}");
        wr.flush();

        connection.connect();

        Assert.assertEquals(409, connection.getResponseCode());
    }
}