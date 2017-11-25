package com.mauriciotogneri.momowars.model.constants;

public enum CellType
{
    BASE,
    GRASS,
    EARTH,
    MOUNTAIN,
    FOREST,
    DESERT,
    CONCRETE,
    WATER,
    ICE;

    public String toString()
    {
        return super.toString().toLowerCase();
    }
}