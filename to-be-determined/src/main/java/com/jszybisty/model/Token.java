package com.jszybisty.model;

import org.springframework.core.serializer.Serializer;
import org.springframework.security.core.Authentication;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Jakub on 8/21/2016.
 */
@Entity
public class Token implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String token;

    private Authentication authentication;

    public Token() {

    }

    public Token(String token, Authentication authentication) {
        this.token = token;
        this.authentication = authentication;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
