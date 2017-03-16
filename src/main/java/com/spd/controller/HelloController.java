package com.spd.controller;

import com.spd.dto.UserDTO;
import com.spd.entity.User;
import com.spd.repository.UserRepository;
import com.spd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

@RestController
@RequestMapping("/")
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return userService.getById(1).getFirstName();
    }

    @RequestMapping(value = "api/v1/greeting", method = RequestMethod.POST)
    public UserDTO getUser() {
        return new UserDTO();
    }
}
