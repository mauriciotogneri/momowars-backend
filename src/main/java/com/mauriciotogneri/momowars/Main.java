package com.mauriciotogneri.momowars;

import com.mauriciotogneri.jerry.Jerry;

public class Main extends Jerry
{
    public static void main(String[] args) throws Exception
    {
        Main main = new Main();
        main.start(Integer.valueOf(System.getenv("PORT")), "com.mauriciotogneri.momowars");
    }
}