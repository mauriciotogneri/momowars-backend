package com.mauriciotogneri.momowars.utils;

import java.io.IOException;
import java.security.MessageDigest;

public class SHA
{
    public static String sha512(String input) throws IOException
    {
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(input.getBytes());

            StringBuilder builder = new StringBuilder();

            for (byte value : messageDigest.digest())
            {
                builder.append(Integer.toString((value & 0xff) + 0x100, 16).substring(1));
            }

            return builder.toString();
        }
        catch (Exception e)
        {
            throw new IOException(e);
        }
    }
}