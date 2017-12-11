package com.mauriciotogneri.momowars.app;

public class AppParameters
{
    public static final int PORT = integer("PORT");
    public static final String DATABASE_URL = string("JDBC_DATABASE_URL");
    public static final int CONNECTION_POOL_SIZE = integer("CONNECTION_POOL_SIZE");
    public static final String GMAIL_USERNAME = string("GMAIL_USERNAME");
    public static final String GMAIL_PASSWORD = string("GMAIL_PASSWORD");
    public static final int REQUESTS_PER_MINUTE = integer("REQUESTS_PER_MINUTE");

    private static String string(String name)
    {
        return System.getenv(name);
    }

    private static int integer(String name)
    {
        try
        {
            return Integer.valueOf(System.getenv(name));
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}