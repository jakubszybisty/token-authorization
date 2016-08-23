package com.jszybisty.config;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;

import java.util.UUID;


@EnableCaching
public class TokenService {

    private Logger logger = LoggerFactory.getLogger(TokenService.class);
    private CacheManager cm = new CacheManager("src/main/resources/encache.xml");
    private Cache cache = (Cache) cm.getEhcache("restApiAuthTokenCache");

    public static final int HALF_AN_HOUR_IN_MILLISECONDS = 30 * 60 * 1000;

    @Scheduled(fixedRate = HALF_AN_HOUR_IN_MILLISECONDS)
    public void evictExpiredTokens() {
        logger.info("Evicting expired tokens");
        cache.evictExpiredElements();
    }

    public String generateNewToken() {
        return UUID.randomUUID().toString();
    }

    public void store(String token, Authentication authentication) {
        cache.put(new Element(token, authentication));
    }

    public boolean contains(String token) {
        return cache.get(token) != null;
    }

    public Authentication retrieve(String token) {
        return (Authentication) cache.get(token).getObjectValue();
    }
}