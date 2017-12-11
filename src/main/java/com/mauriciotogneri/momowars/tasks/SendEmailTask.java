package com.mauriciotogneri.momowars.tasks;

import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.email.Email;

public class SendEmailTask extends Task
{
    private final Email email;

    public SendEmailTask(Email email)
    {
        this.email = email;
    }

    @Override
    public void process(DatabaseConnection connection) throws Exception
    {
        try
        {
            email.send();
        }
        catch (Exception e)
        {
            // ignore
        }
    }
}