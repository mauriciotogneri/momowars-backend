package com.mauriciotogneri.momowars.model.constants;

public enum GameStatus
{
    OPEN,
    PLAYING,
    CALCULATING,
    FINISHED;

    public String toString()
    {
        return super.toString().toLowerCase();
    }
}