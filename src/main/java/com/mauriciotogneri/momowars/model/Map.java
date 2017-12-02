package com.mauriciotogneri.momowars.model;

import java.util.List;

public class Map
{
    private final Long id;
    private final String name;
    private final Integer width;
    private final Integer height;
    private final List<Cell> cells;

    public Map(Long id, String name, Integer width, Integer height, List<Cell> cells)
    {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.cells = cells;
    }

    public Long id()
    {
        return id;
    }
}