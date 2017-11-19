package com.mauriciotogneri.momowars;

import java.io.File;
import java.io.RandomAccessFile;

public class Resources
{
    public static String content(String path) throws Exception
    {
        ClassLoader classLoader = Resources.class.getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());

        RandomAccessFile accessFile = new RandomAccessFile(file, "r");
        byte[] buffer = new byte[(int) accessFile.length()];
        accessFile.readFully(buffer);

        return new String(buffer, "UTF-8");
    }
}