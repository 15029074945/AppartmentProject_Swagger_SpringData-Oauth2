package com.spd.security;

import com.spd.entity.User;
import com.spd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by Sasha on 16.03.2017.
 */

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private UserService userService;

    @Autowired
    public CustomAuthenticationManager(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        User user = userService.findByEmail(email);

        if (user == null) {
            throw new BadCredentialsException("1000");
        }

        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("1000");
        }

        return new UsernamePasswordAuthenticationToken(email, password);  /*new SimpleGrantedAuthority("AUTHORIZED"));*/
    }
}
