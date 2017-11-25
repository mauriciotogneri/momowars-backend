package com.mauriciotogneri.momowars.model.headers;

import com.mauriciotogneri.jsonschema.annotations.Name;

public class SessionToken
{
    @Name("Session-Token")
    public String token;
}