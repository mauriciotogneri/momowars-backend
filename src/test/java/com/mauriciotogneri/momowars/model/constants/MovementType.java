package com.mauriciotogneri.momowars.model.constants;

public enum MovementType
{
    NONE,
    UP_LEFT,
    UP,
    UP_RIGHT,
    DOWN_RIGHT,
    DOWN,
    DOWN_LEFT;

    public String toString()
    {
        return super.toString().toLowerCase();
    }
}