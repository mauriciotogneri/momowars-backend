package com.mauriciotogneri.momowars.federation;

public class FederationIdentity
{
    private final String email;
    private final String name;
    private final String picture;
    private final Boolean emailValidated;

    public FederationIdentity(String email, String name, String picture, Boolean emailValidated)
    {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.emailValidated = emailValidated;
    }

    public String email()
    {
        return email;
    }

    public String name()
    {
        return name;
    }

    public String picture()
    {
        return picture;
    }
}