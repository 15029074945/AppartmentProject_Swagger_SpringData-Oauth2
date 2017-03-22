package com.spd.controller;

import com.spd.bean.UserTelephoneBean;
import com.spd.service.UserTelephoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public void addExtraTelephone(Authentication authentication, @PathVariable("telephone") String telephone) {
        userTelephoneService.saveUserTelephone(authentication.getName(), telephone);
    }

    @RequestMapping(value = "/{idTelephone}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete extra telephone", httpMethod = "DELETE")
    public void deleteExtraTelephone(@PathVariable("idTelephone") int idTelephone) {
        userTelephoneService.deleteUserTelephone(idTelephone);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "get list extra telephones", httpMethod = "GET")
    public List<UserTelephoneBean> getUserExtraEmails(Authentication authentication) {
        return userTelephoneService.getListByEmail(authentication.getName());
    }

}
