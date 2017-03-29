package com.spd.controller;

import com.spd.bean.ImageBean;
import com.spd.bean.UserInformationBean;
import com.spd.bean.UserRegistrationBean;
import com.spd.entity.User;
import com.spd.exception.ValidationException;
import com.spd.mapper.ObjectMapper;
import com.spd.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
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
    public ResponseEntity<String> createUser(Authentication authentication, @RequestBody UserRegistrationBean userRegistrationBean) {
        Optional<Authentication> authenticationOptional = Optional.ofNullable(authentication);
        if (authenticationOptional.isPresent()) {
            // TODO
            return new ResponseEntity<>("Can not create a new user", HttpStatus.BAD_REQUEST);
        }
        else {
            ValidationException
                    .assertTrue(userRegistrationBean.getTermsChecked(), "User shutdown exception handling");

            User user = null;
            try {
                user = userService.createUser(userRegistrationBean);
            } catch (Exception e) {
                return new ResponseEntity<>("Error save. User is registered", HttpStatus.BAD_REQUEST);
            }
            try {
                userService.registration(user);
            } catch (MessagingException e) {
                return new ResponseEntity<>("Error send message", HttpStatus.OK);
            }
            return new ResponseEntity<>("Message send", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/pass/{password}", method = RequestMethod.POST)
    @ApiOperation(value = "change password", httpMethod = "POST")
    public void changePassword(Authentication authentication, @PathVariable("password") String password) {
        userService.changePassword(authentication.getName(), password);
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ApiOperation(value = "verify user", httpMethod = "POST")
    public void verificationUser(@PathVariable String token) {
        userService.verification(token);
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

    @RequestMapping(value = "/{id}/image", method = RequestMethod.PUT)
    @ApiOperation(value = "set image", httpMethod = "PUT")
    public void setUserImage(@PathVariable("id") int id, @RequestBody ImageBean imageBean) {
        userService.setImage(id, imageBean.getId());
    }

}
