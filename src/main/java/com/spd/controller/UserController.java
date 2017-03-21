package com.spd.controller;

import com.spd.bean.UserInformationBean;
import com.spd.bean.UserRegistrationBean;
import com.spd.entity.User;
import com.spd.mapper.ObjectMapper;
import com.spd.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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
    @ApiOperation(value = "registration", httpMethod = "POST")
    public void registration(@RequestBody UserRegistrationBean userRegistrationBean) {
        if (userRegistrationBean.getTermsChecked()) {

            User user = objectMapper.map(userRegistrationBean, User.class);

            userService.saveUser(user);

            // TODO
            // send email verification
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete user", httpMethod = "DELETE")
    public void deleteUser(@PathVariable("id") int id) {

        userService.deleteUser(id);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "get user", httpMethod = "GET")
    public UserInformationBean getUser(@PathVariable("id") int id) {

        User user = userService.getById(id);

        return objectMapper.map(user, UserInformationBean.class);
    }

    //@RequestMapping(value = "/{id}/{property}/{value}", method = RequestMethod.PUT)
    //@ApiOperation(value = "change property user", httpMethod = "PUT")
    public void changeUser(@PathVariable("id") int id, @PathVariable("property") String property, @PathVariable("value") String value) {
        User user = userService.getById(id);
        // TODO
        // replace switch
        switch (property) {
            case "email":
                user.setEmail(value);
                break;
            case "password":
                user.setPassword(value);
                break;
            case "firstName":
                user.setFirstName(value);
                break;
            case "lastName":
                user.setLastName(value);
                break;
            // TODO
            // add image
            default:
                return;
        }
        userService.updateUser(user);
    }

}
