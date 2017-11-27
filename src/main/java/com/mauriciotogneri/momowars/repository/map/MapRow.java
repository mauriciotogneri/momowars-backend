package com.mauriciotogneri.momowars.repository.map;

import com.mauriciotogneri.momowars.model.Map;

public class MapRow
{
    public Long id;
    public String name;
    public Integer width;
    public Integer height;

    public Map map()
    {
        return new Map(name, width, height);
    }
}