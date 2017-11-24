package com.mauriciotogneri.momowars;

import com.mauriciotogneri.jerry.Jerry;
import com.mauriciotogneri.momowars.database.Database;

public class Main extends Jerry
{
    private final int port;
    private final Mode mode;
    public static Database database;

    public Main(int port, Mode mode, String databaseUrl, int connectionPoolSize) throws Exception
    {
        this.port = port;
        this.mode = mode;
        database = new Database(databaseUrl, connectionPoolSize);
    }

    public void start() throws Exception
    {
        start(port, mode, getClass().getPackage());
    }

    public static void main(String[] args) throws Exception
    {
        int port = Integer.valueOf(System.getenv("PORT"));
        String databaseUrl = System.getenv("JDBC_DATABASE_URL");
        int connectionPoolSize = Integer.valueOf(System.getenv("CONNECTION_POOL_SIZE"));

        Main main = new Main(port, Mode.LOCAL, databaseUrl, connectionPoolSize);
        main.start();
    }
}