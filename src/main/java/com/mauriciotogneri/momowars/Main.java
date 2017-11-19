package com.mauriciotogneri.momowars;

import com.mauriciotogneri.jerry.Jerry;

import org.eclipse.jetty.server.Server;

public class Main extends Jerry
{
    public static void main(String[] args) throws Exception
    {
        //        Main main = new Main();
        //        main.start(8080, "com.mauriciotogneri.momowars");

        Server server = new Server(8080);
        server.setHandler(new HelloHandler());

        server.start();
        server.join();
    }
}