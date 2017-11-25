package com.mauriciotogneri.momowars;

import com.mauriciotogneri.jerry.Jerry.Mode;

import org.eclipse.jetty.server.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseTest
{
    private static Server server;

    @BeforeClass
    public static void startJetty() throws Exception
    {
        //int port = Integer.valueOf(System.getenv("PORT"));
        //String databaseUrl = System.getenv("JDBC_DATABASE_URL");
        //int connectionPoolSize = Integer.valueOf(System.getenv("CONNECTION_POOL_SIZE"));

        Main main = new Main(5000, Mode.LOCAL, "jdbc:postgresql://localhost:5432/momowars?user=postgres&password=password", 3);
        server = main.server();
        server.start();
    }

    @AfterClass
    public static void stopJetty()
    {
        try
        {
            server.stop();
        }
        catch (Exception e)
        {
            // ignore
        }
    }
}