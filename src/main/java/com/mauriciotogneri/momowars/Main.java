package com.mauriciotogneri.momowars;

import com.mauriciotogneri.jerry.Jerry;
import com.mauriciotogneri.momowars.email.Email;

public class Main extends Jerry
{
    public static void main(String[] args) throws Exception
    {
        Email email = new Email(
                "maxpawer@gmail.com",
                "mauricio.togneri@gmail.com",
                "Test",
                "Working!");
        email.send();

        Main main = new Main();
        main.start(Integer.valueOf(System.getenv("PORT")), "com.mauriciotogneri.momowars");
    }
}