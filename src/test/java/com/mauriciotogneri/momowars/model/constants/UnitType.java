package com.mauriciotogneri.momowars.model.constants;

public enum UnitType
{
    SQUAD,
    MORTAR,
    HUMVEE,
    LIGHT_TANK,
    HEAVY_TANK,
    HELICOPTER;

    public String toString()
    {
        return super.toString().toLowerCase();
    }
}