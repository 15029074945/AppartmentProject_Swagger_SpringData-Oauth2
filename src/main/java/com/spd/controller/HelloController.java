package com.spd.controller;

import com.spd.dto.UserRegistrationDTO;
import com.spd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return userService.getById(1).getFirstName();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String registration(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        System.out.println(userRegistrationDTO.firstName);
        return "okey";
    }

    @RequestMapping(value = "api/v1/greeting", method = RequestMethod.POST)
    public UserRegistrationDTO getUser() {
        return new UserRegistrationDTO();
    }
}
