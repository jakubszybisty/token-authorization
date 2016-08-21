package com.jszybisty.model;

import com.sun.deploy.security.ValidationState;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "person")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String login;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private enum Role {
        NORMAL,
        SUPER
    }

    public User(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
