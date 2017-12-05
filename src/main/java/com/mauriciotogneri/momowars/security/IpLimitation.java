package com.mauriciotogneri.momowars.security;

import com.mauriciotogneri.momowars.util.MemoryCache;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class IpLimitation
{
    private final MemoryCache<String, Integer> cache = new MemoryCache<>(
            10, TimeUnit.SECONDS, // cleanup time
            60, TimeUnit.SECONDS // expiration time
    );

    private static final int REQUESTS_LIMIT = 10;

    public void check(ContainerRequestContext request, String ip)
    {
        Optional<Integer> count = cache.get(ip);

        if (count.isPresent())
        {
            int value = count.get();

            if (value < REQUESTS_LIMIT)
            {
                cache.put(ip, value + 1);
            }
            else
            {
                request.abortWith(Response.status(Status.TOO_MANY_REQUESTS).build());
            }
        }
        else
        {
            cache.put(ip, 1);
        }
    }
}