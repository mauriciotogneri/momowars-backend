package com.mauriciotogneri.momowars.model;

public class Map
{
    private final String name;
    private final Integer width;
    private final Integer height;

    public Map(String name, Integer width, Integer height)
    {
        this.name = name;
        this.width = width;
        this.height = height;
    }
}