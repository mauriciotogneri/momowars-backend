package com.mauriciotogneri.momowars.federation;

public class FederationEntity
{
    private final String email;
    private final String name;
    private final String picture;

    public FederationEntity(String email, String name, String picture)
    {
        this.email = email;
        this.name = name;
        this.picture = picture;
    }
}