package com.mauriciotogneri.momowars.federation;

import java.util.Optional;

public interface FederationIdentityProvider
{
    Optional<FederationIdentity> identity();
}