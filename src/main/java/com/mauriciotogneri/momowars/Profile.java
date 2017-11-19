package com.mauriciotogneri.momowars;

public class Profile
{
    public final Long id;
    public final String email;
    public final String nickname;

    public Profile(Long id, String email, String nickname)
    {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }
}