package com.spd.config;

import com.spd.entity.User;
import com.spd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    @Autowired
    public LoginAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String authenticationEmail = authentication.getName();
        String authenticationPassword = authentication.getCredentials().toString();
        String userEmail = null;
        String userPassword = null;
        Optional<User> userOptional = userService.getByEmail(authenticationEmail);
        User user = null;
        Boolean status = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            userEmail = user.getEmail();
            userPassword = user.getPassword();
            status = user.getStatus();
        }
        else {
            throw new AuthenticationExceptionImpl("Error email");
        }
        if (!authenticationEmail.equals(userEmail)) {
            throw new AuthenticationExceptionImpl("Error email");
        }
        else if (!authenticationPassword.equals(userPassword)) {
            throw new AuthenticationExceptionImpl("Error password");
        }
        else if (!status) {
            userService.repeatSendEmail(user);
            throw new AuthenticationExceptionImpl("Authorize your mail");
        }
        else {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            return new UsernamePasswordAuthenticationToken(
                    authenticationEmail,
                    authenticationPassword,
                    grantedAuths);
        }
    }

    public class AuthenticationExceptionImpl extends RuntimeException {

        public AuthenticationExceptionImpl(String msg, Throwable t) {
            super(msg, t);
        }

        public AuthenticationExceptionImpl(String msg) {
            super(msg);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
