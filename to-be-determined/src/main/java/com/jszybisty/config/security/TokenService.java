package com.jszybisty.config.security;


import com.jszybisty.dao.TokenRepository;
import com.jszybisty.model.Token;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public class TokenService {

    @Autowired
    TokenRepository tokenRepository;

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
    private static final Cache restApiAuthTokenCache = CacheManager.getInstance().getCache("restApiAuthTokenCache");
    public static final int HALF_AN_HOUR_IN_MILLISECONDS = 30 * 60 * 1000;

//    @Scheduled(fixedRate = HALF_AN_HOUR_IN_MILLISECONDS)
//    public void evictExpiredTokens() {
//        logger.info("Evicting expired tokens");
//        restApiAuthTokenCache.evictExpiredElements();
//    }

    public String generateNewToken() {
        return UUID.randomUUID().toString();
    }

    public void store(String token, Authentication authentication) {
        //restApiAuthTokenCache.put(new Element(token, authentication));
        tokenRepository.save(new Token(token, authentication));
    }

    public boolean contains(String token) {

        if(tokenRepository.findByToken(token) != null)
            return true;
        else
            return false;
    }

    public Authentication retrieve(String token) {
        //return (Authentication) restApiAuthTokenCache.get(token).getObjectValue();
        Token token2 = tokenRepository.findByToken(token);
        return token2.getAuthentication();

    }
}