package com.jszybisty.config.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;



public class TokenResponse {

    @JsonProperty
    private String token;

    @JsonProperty
    private String authenticated;

    public TokenResponse() {

    }

    public TokenResponse(String token) {
        this.token = token;
    }

    public TokenResponse(String authenticated, String token) {
        this.authenticated = authenticated;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(String authenticated) {
        this.authenticated = authenticated;
    }
}