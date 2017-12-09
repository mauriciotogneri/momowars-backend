package com.mauriciotogneri.momowars.app;

import com.mauriciotogneri.jerry.Jerry;
import com.mauriciotogneri.jerry.config.JerryConfig;
import com.mauriciotogneri.momowars.database.Database;
import com.mauriciotogneri.momowars.server.Application;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

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
        JerryConfig.Builder config = new JerryConfig.Builder();
        config.port(port);
        config.host("0.0.0.0");
        config.handlers(handlers());

        return new Jerry().create(config.build());
    }

    private HandlerList handlers()
    {
        String publicDir = getClass().getClassLoader().getResource("public").toExternalForm();

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase(publicDir);

        ContextHandler contextHandler = new ContextHandler(".");
        contextHandler.setHandler(resourceHandler);

        ServletHolder servlet = new ServletHolder(new ServletContainer(new Application()));
        ServletContextHandler servletContext = new ServletContextHandler();
        servletContext.setContextPath("/");
        servletContext.addServlet(servlet, "/*");

        return new HandlerList(contextHandler, servletContext);
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