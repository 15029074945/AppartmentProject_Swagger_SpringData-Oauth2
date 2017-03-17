package com.spd.controller;

import com.spd.dto.UserRegistrationDTO;
import com.spd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void registration(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        //userService.saveUser(userRegistrationDTO);
    }

    @RequestMapping(value = "api/v1/greeting", method = RequestMethod.GET)
    public UserRegistrationDTO getUser() {
        return new UserRegistrationDTO();
    }
}
