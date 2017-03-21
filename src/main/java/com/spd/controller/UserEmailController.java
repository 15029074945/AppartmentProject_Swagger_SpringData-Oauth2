package com.spd.controller;

import com.spd.bean.UserEmailBean;
import com.spd.service.UserEmailService;
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
@RequestMapping("api/v1/users/{id}/emails")
@Api(value = "users")
public class UserEmailController {

    private final UserEmailService userEmailService;

    @Autowired
    public UserEmailController(UserEmailService userEmailService) {
        this.userEmailService = userEmailService;
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.POST)
    @ApiOperation(value = "add extra email", httpMethod = "POST")
    public void addExtraEmail(@PathParam("id") int id, @PathParam("email") String email) {
        userEmailService.saveUserEmail(id, email);
    }

    @RequestMapping(value = "/{idEmail}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete extra email", httpMethod = "DELETE")
    public void deleteExtraEmail(@PathParam("idEmail") int idEmail) {
        userEmailService.deleteUserEmail(idEmail);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "get list extra emails", httpMethod = "GET")
    public List<UserEmailBean> getUserExtraEmails(@PathParam("id") int id) {
        return userEmailService.getListByUserId(id);
    }

}
