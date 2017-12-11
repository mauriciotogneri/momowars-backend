package com.mauriciotogneri.momowars.security;

import com.mauriciotogneri.momowars.util.MemoryCache;

import java.net.InetAddress;
import java.net.NetworkInterface;
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

    private static final int REQUESTS_LIMIT = 60;

    public void check(ContainerRequestContext request, String ip)
    {
        if (!isLocalhost(ip))
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

    private boolean isLocalhost(String ip)
    {
        try
        {
            InetAddress address = InetAddress.getByName(ip);

            // check if the address is a valid special local or loop back
            if (address.isAnyLocalAddress() || address.isLoopbackAddress() || address.isSiteLocalAddress())
            {
                return true;
            }

            // check if the address is defined on any interface
            return (NetworkInterface.getByInetAddress(address) != null);
        }
        catch (Exception e)
        {
            return false;
        }
    }
}