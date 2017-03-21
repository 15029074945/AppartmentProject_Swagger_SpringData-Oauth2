package com.spd.controller;

import com.spd.bean.UserTelephoneBean;
import com.spd.service.UserTelephoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("api/v1/users/{id}/telephones")
@Api(value = "users")
public class UserTelephoneController {

    private final UserTelephoneService userTelephoneService;

    @Autowired
    public UserTelephoneController(UserTelephoneService userTelephoneService) {
        this.userTelephoneService = userTelephoneService;
    }

    @RequestMapping(value = "/{telephone}", method = RequestMethod.POST)
    @ApiOperation(value = "add extra telephone", httpMethod = "POST")
    public void addExtraTelephone(@PathParam("id") int id, @PathParam("telephone") String telephone) {
        userTelephoneService.saveUserTelephone(id, telephone);
    }

    @RequestMapping(value = "/{idTelephone}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete extra telephone", httpMethod = "DELETE")
    public void deleteExtraTelephone(@PathParam("idTelephone") int idTelephone) {
        userTelephoneService.deleteUserTelephone(idTelephone);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "get list extra telephones", httpMethod = "GET")
    public List<UserTelephoneBean> getUserExtraEmails(@PathParam("id") int id) {
        return userTelephoneService.getListByUserId(id);
    }

}
