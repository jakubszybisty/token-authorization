package com.jszybisty.config.externalwebservice;


import com.jszybisty.config.security.AuthenticationWithToken;

public interface ExternalServiceAuthenticator {

    AuthenticationWithToken authenticate(String username, String password);
}
