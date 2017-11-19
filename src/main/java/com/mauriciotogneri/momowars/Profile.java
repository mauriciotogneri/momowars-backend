package com.mauriciotogneri.momowars;

public class Profile
{
    public final Long id;
    public final String email;
    public final String name;

    public Profile(Long id, String email, String name)
    {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public boolean isValid()
    {
        return true;
    }
}