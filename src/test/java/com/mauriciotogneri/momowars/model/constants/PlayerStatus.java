package com.mauriciotogneri.momowars.model.constants;

public enum PlayerStatus
{
    PLAYING,
    WAITING,
    VICTORIOUS,
    DEFEATED,
    SURRENDED;

    public String toString()
    {
        return super.toString().toLowerCase();
    }
}