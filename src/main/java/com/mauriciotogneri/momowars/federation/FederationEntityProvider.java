package com.mauriciotogneri.momowars.federation;

import java.util.Optional;

public interface FederationEntityProvider
{
    Optional<FederationEntity> entity();
}