package com.mauriciotogneri.momowars.federation;

public class FederationEntity
{
    private final String email;
    private final String name;
    private final String picture;
    private final Boolean emailValidated;

    public FederationEntity(String email, String name, String picture, Boolean emailValidated)
    {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.emailValidated = emailValidated;
    }
}