package com.mauriciotogneri.momowars;

import com.mauriciotogneri.jerry.Jerry;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main extends Jerry
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(9999);
        HttpConfiguration https = new HttpConfiguration();
        https.addCustomizer(new SecureRequestCustomizer());
        ServerConnector sslConnector = new ServerConnector(server,
                                                           new SslConnectionFactory("http/1.1"),
                                                           new HttpConnectionFactory(https));
        sslConnector.setPort(9998);
        server.setConnectors(new Connector[] {connector, sslConnector});

        ServletContextHandler context = new ServletContextHandler(1);
        context.setContextPath("/");
        server.setHandler(context);
        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "com.mauriciotogneri.momowars");

        try
        {
            server.start();
            server.join();
        }
        finally
        {
            server.destroy();
        }


        //Main main = new Main();
        //main.start(8080, "com.mauriciotogneri.momowars");
    }
}