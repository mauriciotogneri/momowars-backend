package com.mauriciotogneri.momowars.federation;

import com.google.gson.Gson;
import com.mauriciotogneri.javautils.Strings;

import java.io.IOException;
import java.util.Optional;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GoogleIdentity implements FederationIdentityProvider
{
    private final String token;
    private static final String CLIENT_ID = "320342709224-uun9uafeffdlr89hbodlnqnck0iimja9.apps.googleusercontent.com";

    public GoogleIdentity(String token)
    {
        this.token = token;
    }

    @Override
    public Optional<FederationIdentity> identity()
    {
        try
        {
            HttpResponse response = response();

            if (response.isValid(CLIENT_ID))
            {
                return Optional.of(new FederationIdentity(response.email, response.name(), response.picture, response.email_verified));
            }
            else
            {
                return Optional.empty();
            }
        }
        catch (Exception e)
        {
            return Optional.empty();
        }
    }

    private HttpResponse response() throws IOException
    {
        String url = String.format("https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=%s", token);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        String body = response.body().string();
        Gson gson = new Gson();

        return gson.fromJson(body, HttpResponse.class);
    }

    public static class HttpResponse
    {
        public String aud;
        public String email;
        public Boolean email_verified;
        public String name;
        public String given_name;
        public String picture;
        public String locale;

        public String name()
        {
            if (Strings.isNotEmpty(given_name))
            {
                return given_name;
            }
            else
            {
                return name;
            }
        }

        public boolean isValid(String clientId)
        {
            return Strings.equals(aud, clientId);
        }
    }
}