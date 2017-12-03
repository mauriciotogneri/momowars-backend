package com.mauriciotogneri.momowars.api.model.games;

import com.mauriciotogneri.jsonschema.annotations.MinLength;
import com.mauriciotogneri.jsonschema.annotations.Minimum;

public class Map
{
    public Long id;

    public String name;

    @Minimum(1)
    public Integer width;

    @Minimum(1)
    public Integer height;

    @MinLength(1)
    public Cell[] cells;
}