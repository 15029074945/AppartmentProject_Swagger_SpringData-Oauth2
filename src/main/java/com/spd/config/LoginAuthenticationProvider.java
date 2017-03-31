package com.spd.config;

import com.spd.entity.User;
import com.spd.exception.AuthenticationUserException;
import com.spd.service.RegistrationService;
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
    private final RegistrationService registrationService;

    @Autowired
    public LoginAuthenticationProvider(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String authenticationEmail = authentication.getName();
        String authenticationPassword = authentication.getCredentials().toString();
        String userEmail = null;
        String userPassword = null;
        Optional<User> userOptional = userService.getUserOptionalByEmail(authenticationEmail);
        User user = null;
        Boolean status = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            userEmail = user.getEmail();
            userPassword = user.getPassword();
            status = user.getStatus();
        }
        else {
            throw new AuthenticationUserException("Error email");
        }
        if (!authenticationEmail.equals(userEmail)) {
            throw new AuthenticationUserException("Error email");
        }
        else if (!authenticationPassword.equals(userPassword)) {
            throw new AuthenticationUserException("Error password");
        }
        else if (!status) {
            registrationService.repeatSendEmail(user);
            throw new AuthenticationUserException("Authorize your mail");
        }
        else {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            return new UsernamePasswordAuthenticationToken(
                    authenticationEmail,
                    authenticationPassword,
                    grantedAuths);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
