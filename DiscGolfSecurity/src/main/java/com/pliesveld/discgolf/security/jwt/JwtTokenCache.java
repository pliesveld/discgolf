package com.pliesveld.discgolf.security.jwt;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.pliesveld.discgolf.common.logging.Markers;
import com.pliesveld.discgolf.config.SpringCacheConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Patrick Liesveld
 */
@Component
public class JwtTokenCache {
    private static final Logger LOG = LogManager.getLogger();

    @Cacheable(cacheNames = SpringCacheConfig.CacheConstants.TOKEN_CACHE, key = "#token", unless = "#result == null")
    public UserDetails findUserByTokenCache(String token) {
        LOG.debug(Markers.SECURITY_CACHE, "Checking cache for {}", () -> token);
        return null;
    }

    @CachePut(cacheNames = SpringCacheConfig.CacheConstants.TOKEN_CACHE, key = "#token")
    public UserDetails cacheUserByToken(String token, UserDetails user) {
        LOG.debug(Markers.SECURITY_CACHE, "Caching token {} to user {}", token, user);
        return user;
    }
}
