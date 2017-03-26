package com.spd.controller;

import com.spd.bean.UserInformationBean;
import com.spd.bean.UserRegistrationBean;
import com.spd.exception.ValidationException;
import com.spd.mapper.ObjectMapper;
import com.spd.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestController
@RequestMapping("api/v1/users")
@Api(value = "users")
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "create update user", httpMethod = "POST")
    public void createUser(Authentication authentication, @RequestBody UserRegistrationBean userRegistrationBean) {
        Optional<Authentication> authenticationOptional = Optional.ofNullable(authentication);
        if (authenticationOptional.isPresent()) {
            // TODO
        }
        else {
            ValidationException
                    .assertTrue(userRegistrationBean.getTermsChecked(), "User shutdown exception handling");
            userService.saveUser(userRegistrationBean);
            // TODO
            // send email verification
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "create update user", httpMethod = "PUT")
    public void updateUser(Authentication authentication, @RequestBody UserInformationBean userInformationBean) {
        userService
                .updateUser(Optional
                        .ofNullable(authentication)
                        .map(Principal::getName), userInformationBean);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "get user", httpMethod = "GET")
    public UserInformationBean getUser(Authentication authentication) {
        return userService
                .getByEmail(authentication.getName())
                .map(user -> objectMapper.map(user, UserInformationBean.class))
                .orElse(new UserInformationBean());
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete user", httpMethod = "DELETE")
    public void deleteUser(Authentication authentication) {
        userService.deleteUser(authentication.getName());
    }

}
