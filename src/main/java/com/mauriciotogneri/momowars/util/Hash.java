package com.mauriciotogneri.momowars.util;

import com.mauriciotogneri.javautils.Encoding;
import com.mauriciotogneri.jerry.exceptions.server.InternalServerErrorException;

import java.security.NoSuchAlgorithmException;

public class Hash
{
    public static String of(String input)
    {
        try
        {
            return Encoding.sha512(input);
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new InternalServerErrorException(e);
        }
    }
}