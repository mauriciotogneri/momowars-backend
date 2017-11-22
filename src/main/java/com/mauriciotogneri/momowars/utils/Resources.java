package com.mauriciotogneri.momowars.utils;

import com.mauriciotogneri.jerry.exceptions.server.InternalServerErrorException;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Resources
{
    public static String content(String path)
    {
        try
        {
            ClassLoader classLoader = Resources.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(path);

            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read;

            while ((read = inputStream.read(buffer)) != -1)
            {
                result.write(buffer, 0, read);
            }

            return result.toString(StandardCharsets.UTF_8.name());
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e);
        }
    }

    public static void close(AutoCloseable closeable)
    {
        try
        {
            closeable.close();
        }
        catch (Exception e)
        {
            // ignore
        }
    }
}