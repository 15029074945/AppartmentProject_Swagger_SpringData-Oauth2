package com.spd.service;

import com.spd.bean.UserRegistrationBean;
import com.spd.exception.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class CheckService {

    public void checkUserTerm(UserRegistrationBean userRegistrationBean) {
        ValidationException
                .assertTrue(userRegistrationBean.getTermsChecked(), "User shutdown exception handling");
    }

}
