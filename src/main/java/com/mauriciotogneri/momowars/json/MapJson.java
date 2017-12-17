package com.mauriciotogneri.momowars.json;

import java.util.List;

public class MapJson
{
    private final Long id;
    private final String name;
    private final Integer width;
    private final Integer height;
    private final List<CellJson> cells;

    public MapJson(Long id, String name, Integer width, Integer height, List<CellJson> cells)
    {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.cells = cells;
    }
}