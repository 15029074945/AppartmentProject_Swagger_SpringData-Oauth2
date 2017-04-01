package com.spd.controller;

import com.spd.bean.UserTelephoneBean;
import com.spd.service.UserTelephoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/telephones")
@Api(value = "users")
public class UserTelephoneController {

    private final UserTelephoneService userTelephoneService;

    @Autowired
    public UserTelephoneController(UserTelephoneService userTelephoneService) {
        this.userTelephoneService = userTelephoneService;
    }

    @RequestMapping(value = "/{telephone}", method = RequestMethod.POST)
    @ApiOperation(value = "add extra telephone", httpMethod = "POST")
    public UserTelephoneBean addExtraTelephone(Authentication authentication, @RequestBody UserTelephoneBean userTelephoneBean) {
        return userTelephoneService.saveUserTelephone(authentication.getName(), userTelephoneBean.getTelephone());
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "update extra telephone", httpMethod = "PUT")
    public void updateExtraEmail(Authentication authentication, @RequestBody UserTelephoneBean userTelephoneBean) {
        userTelephoneService.updateUserTelephone(authentication.getName(), userTelephoneBean);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete extra telephone", httpMethod = "DELETE")
    public void deleteExtraTelephone(Authentication authentication, @PathVariable("id") int id) {
        userTelephoneService.deleteUserTelephone(authentication.getName(), id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "get list extra telephones", httpMethod = "GET")
    public List<UserTelephoneBean> getExtraEmails(Authentication authentication) {
        return userTelephoneService.getListByEmail(authentication.getName());
    }

}
