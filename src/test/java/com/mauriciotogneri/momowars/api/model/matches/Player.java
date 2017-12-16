package com.mauriciotogneri.momowars.api.model.matches;

import com.mauriciotogneri.jsonschema.annotations.Optional;
import com.mauriciotogneri.momowars.types.PlayerStatus;

public class Player
{
    @Optional
    public Long id;

    public String nickname;

    @Optional
    public Integer resources;

    public PlayerStatus status;
}