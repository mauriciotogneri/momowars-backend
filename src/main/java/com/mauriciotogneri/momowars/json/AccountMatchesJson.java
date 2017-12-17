package com.mauriciotogneri.momowars.json;

import java.util.List;

public class AccountMatchesJson
{
    private final List<Long> open;
    private final List<Long> playing;
    private final List<Long> finished;

    public AccountMatchesJson(List<Long> open, List<Long> playing, List<Long> finished)
    {
        this.open = open;
        this.playing = playing;
        this.finished = finished;
    }
}