package com.spd.security;

import com.spd.entity.User;
import com.spd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
*/


@Component
public class CustomAuthenticationManager implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    public CustomAuthenticationManager(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();

        String password = authentication.getCredentials().toString();

        User user = userService.findByEmail(email);

        if (user == null) {
            throw new BadCredentialsException("No user found with such mail!");
        }

        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Incorrect password!");
        }

        return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
