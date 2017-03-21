package com.spd.controller;

import com.spd.bean.UserEmailBean;
import com.spd.entity.UserEmail;
import com.spd.mapper.ObjectMapper;
import com.spd.service.UserEmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/{id}/emails")
@Api(value = "users")
public class UserEmailController {

    private final UserEmailService userEmailService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserEmailController(UserEmailService userEmailService, ObjectMapper objectMapper) {
        this.userEmailService = userEmailService;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.POST)
    @ApiOperation(value = "add extra email", httpMethod = "POST")
    public UserEmailBean addExtraEmail(@PathVariable("id") int id, @PathVariable("email") String email) {
        userEmailService.saveUserEmail(id, email);
        UserEmail userEmail = userEmailService.getUserEmail(email);
        return objectMapper.map(userEmail, UserEmailBean.class);
    }

    @RequestMapping(value = "/{idEmail}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete extra email", httpMethod = "DELETE")
    public void deleteExtraEmail(@PathVariable("idEmail") int idEmail) {
        userEmailService.deleteUserEmail(idEmail);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "get list extra emails", httpMethod = "GET")
    public List<UserEmailBean> getUserExtraEmails(@RequestParam int id) {
        return userEmailService.getListByUserId(id);
    }

}
