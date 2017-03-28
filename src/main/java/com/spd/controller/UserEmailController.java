package com.spd.controller;

import com.spd.bean.UserEmailBean;
import com.spd.entity.UserEmail;
import com.spd.mapper.ObjectMapper;
import com.spd.service.UserEmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/emails")
@Api(value = "users")
public class UserEmailController {

    private final UserEmailService userEmailService;

    @Autowired
    public UserEmailController(UserEmailService userEmailService) {
        this.userEmailService = userEmailService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "add extra email", httpMethod = "POST")
    public UserEmailBean addExtraEmail(Authentication authentication, @RequestBody String email) {
        return userEmailService.saveUserEmail(authentication.getName(), email);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "update extra email", httpMethod = "PUT")
    public void updateExtraEmail(Authentication authentication, @RequestBody UserEmailBean userEmailBean) {
        userEmailService.updateUserEmail(authentication.getName(), userEmailBean);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete extra email", httpMethod = "DELETE")
    public void deleteExtraEmail(Authentication authentication, @RequestBody int id) {
        userEmailService.deleteUserEmail(authentication.getName(), id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "get list extra emails", httpMethod = "GET")
    public List<UserEmailBean> getUserExtraEmails(Authentication authentication) {
        return userEmailService.getListByUserEmail(authentication.getName());
    }

}
