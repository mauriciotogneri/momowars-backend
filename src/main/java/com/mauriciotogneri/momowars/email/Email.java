package com.mauriciotogneri.momowars.email;

import com.mauriciotogneri.momowars.app.AppParameters;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email
{
    private final String from;
    private final String to;
    private final String subject;
    private final String content;

    private static Session session;

    public Email(String from, String to, String subject, String content)
    {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    private static synchronized Session session()
    {
        if (session == null)
        {
            session = newSession();
        }

        return session;
    }

    private static Session newSession()
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");

        return Session.getInstance(properties, new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(AppParameters.GMAIL_USERNAME,
                                                  AppParameters.GMAIL_PASSWORD);
            }
        });
    }

    public void send() throws IOException
    {
        try
        {
            MimeMessage mimeMessage = new MimeMessage(session());
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setContent(content, "text/html");

            Transport.send(mimeMessage);
        }
        catch (Exception e)
        {
            throw new IOException(e);
        }
    }

    public static Email create(String to, String subject, String content)
    {
        return new Email("notifications@momowars.com", to, subject, content);
    }
}