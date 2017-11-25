package com.mauriciotogneri.momowars.model.headers;

import com.mauriciotogneri.jsonschema.annotations.Default;
import com.mauriciotogneri.jsonschema.annotations.Name;

public class ApplicationJson
{
    @Name("Content-Type")
    @Default("application/json")
    public String contentType;
}