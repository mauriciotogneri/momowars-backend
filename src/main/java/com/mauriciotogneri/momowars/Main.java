package com.mauriciotogneri.momowars;

import com.mauriciotogneri.jerry.Jerry;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.handler.AbstractHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Main extends Jerry
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server();

        HttpConfiguration http_config = new HttpConfiguration();
        http_config.setSecureScheme("https");
        http_config.setSecurePort(8443);

        ServerConnector httpsConnector = new ServerConnector(server,
                                                             new SslConnectionFactory("http/1.1"),
                                                             new HttpConnectionFactory(http_config));
        httpsConnector.setPort(8443);

        server.setConnectors(new Connector[] {httpsConnector});

        //ServletContextHandler context = new ServletContextHandler(1);
        //context.setContextPath("/");
        //server.setHandler(context);
        server.setHandler(new HelloHandler());
        //ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        //jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "com.mauriciotogneri.momowars");

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

    public static class HelloHandler extends AbstractHandler
    {
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
            response.getWriter().println("<h1>Hello World</h1>");
        }
    }
}