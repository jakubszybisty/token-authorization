package com.jszybisty.config.security;

import com.fasterxml.jackson.annotation.JsonProperty;



public class TokenResponse {

    @JsonProperty
    private String token;


    public TokenResponse() {

    }



    public TokenResponse(String token) {

        this.token = token;

    }

}