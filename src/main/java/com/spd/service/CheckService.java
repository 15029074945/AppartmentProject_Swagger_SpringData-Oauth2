package com.spd.service;

import com.spd.bean.UserRegistrationBean;
import com.spd.exception.AuthenticationUserException;
import com.spd.exception.ValidationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckService {

    public void checkAuthentication(Authentication authentication) {
        Optional.ofNullable(authentication)
                .orElseThrow(() -> new AuthenticationUserException("User not authentication"));
    }

    public void checkUserTerm(UserRegistrationBean userRegistrationBean) {
        ValidationException
                .assertTrue(userRegistrationBean.getTermsChecked(), "User shutdown exception handling");
    }

}
