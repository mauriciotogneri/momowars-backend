package com.mauriciotogneri.momowars.api.model;

import com.mauriciotogneri.jsonschema.annotations.Name;

import javax.ws.rs.DefaultValue;

public class SessionCookie
{
    @Name("Cookie")
    @DefaultValue("session=xxx")
    public String session;
}