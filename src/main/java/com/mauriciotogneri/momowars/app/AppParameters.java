package com.mauriciotogneri.momowars.app;

public class AppParameters
{
    public static final int PORT = Integer.valueOf(System.getenv("PORT"));
    public static final String DATABASE_URL = System.getenv("JDBC_DATABASE_URL");
    public static final int CONNECTION_POOL_SIZE = Integer.valueOf(System.getenv("CONNECTION_POOL_SIZE"));
    public static final String GMAIL_USERNAME = System.getenv("GMAIL_USERNAME");
    public static final String GMAIL_PASSWORD = System.getenv("GMAIL_PASSWORD");
}