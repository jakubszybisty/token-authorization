package com.jszybisty.config;


import com.google.common.base.Optional;
import com.jszybisty.dao.UserRepository;
import com.jszybisty.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;


public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    private UsernamePasswordAuthenticationToken authentication = null;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findByLogin(username);

        if (username.isEmpty() || password.isEmpty()) {
            throw new BadCredentialsException("Wrong credentials");
        }
        if (user == null) {
            throw new BadCredentialsException("No such user");
        }

        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        grantedAuths.add(new SimpleGrantedAuthority(String.valueOf(user.getRole())));

        authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuths);
        return authentication;

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
