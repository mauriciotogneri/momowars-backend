package com.mauriciotogneri.momowars.app;

import com.mauriciotogneri.jerry.Jerry;
import com.mauriciotogneri.jerry.JerryConfig;
import com.mauriciotogneri.momowars.database.Database;
import com.mauriciotogneri.momowars.server.Application;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class Main
{
    private final int port;
    public static Database database;

    public Main(int port, String databaseUrl, int connectionPoolSize) throws Exception
    {
        this.port = port;
        database = new Database(databaseUrl, connectionPoolSize);
    }

    public void start() throws Exception
    {
        migrate();
        Server server = server();

        try
        {
            server.start();
            server.join();
        }
        finally
        {
            server.destroy();
        }
    }

    public void migrate()
    {
        database.migrate();
    }

    public Server server()
    {
        Jerry jerry = new Jerry();

        return jerry.create(new JerryConfig(
                port,
                "localhost",
                new Application(),
                publicContext()
        ));
    }

    private Handler[] publicContext()
    {
        String publicDir = getClass().getClassLoader().getResource("public").toExternalForm();

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase(publicDir);

        ContextHandler contextHandler = new ContextHandler(".");
        contextHandler.setHandler(resourceHandler);

        return new Handler[] {contextHandler};
    }

    public static void main(String[] args) throws Exception
    {
        int port = Integer.valueOf(System.getenv("PORT"));
        String databaseUrl = System.getenv("JDBC_DATABASE_URL");
        int connectionPoolSize = Integer.valueOf(System.getenv("CONNECTION_POOL_SIZE"));

        Main main = new Main(port, databaseUrl, connectionPoolSize);
        main.start();
    }
}