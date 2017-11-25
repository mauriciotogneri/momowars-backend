package com.mauriciotogneri.momowars.app;

import com.mauriciotogneri.stewie.app.Stewie;

import java.io.File;

public class Swagger
{
    public static void main(String[] args) throws Exception
    {
        if (args.length > 0)
        {
            for (String arg : args)
            {
                File file = new File(arg);

                Stewie stewie = new Stewie();
                stewie.generate(file);
            }
        }
        else
        {
            System.err.println("Usage: java -jar swagger.jar PATH_TO_CONFIG_FILE");
        }
    }
}