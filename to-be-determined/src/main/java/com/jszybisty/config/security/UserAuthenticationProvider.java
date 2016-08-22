package com.jszybisty.config.security;


import com.google.common.base.Optional;
import com.jszybisty.dao.UserRepository;
import com.jszybisty.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;



public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<String> username = (Optional) authentication.getPrincipal();
        Optional<String> password = (Optional) authentication.getCredentials();

        User user = userRepository.findByLogin(username.get());

        if (!username.isPresent() || !password.isPresent()) {
            throw new BadCredentialsException("Wrong credentials");
        }

        if (user == null) {
            throw new BadCredentialsException("Wrong credentials");
        }

        return new UsernamePasswordAuthenticationToken(username, password);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
