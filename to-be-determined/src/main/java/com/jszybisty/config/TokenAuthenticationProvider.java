package com.jszybisty.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class TokenAuthenticationProvider implements AuthenticationProvider {

    private TokenService tokenService;

    public TokenAuthenticationProvider(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = null;
        if (authentication.getPrincipal() != null) {

            token = authentication.getPrincipal().toString();
        }
        if (token == null || token.isEmpty()) {
            throw new BadCredentialsException("Invalid token");
        }
        if (!tokenService.contains(token)) {
            throw new BadCredentialsException("Invalid token or token expired");
        }
        return tokenService.retrieve(token);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }
}