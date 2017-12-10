package com.mauriciotogneri.momowars.tasks;

import com.mauriciotogneri.momowars.email.Email;

public class SendEmailTask extends Task
{
    private final Email email;

    public SendEmailTask(Email email)
    {
        this.email = email;
    }

    @Override
    public void run()
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