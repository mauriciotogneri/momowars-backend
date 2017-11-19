package com.mauriciotogneri.momowars;

import com.mauriciotogneri.jerry.EntityProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileProvider extends EntityProvider<Profile>
{
    public ProfileProvider()
    {
        super(Profile.class);
    }
}