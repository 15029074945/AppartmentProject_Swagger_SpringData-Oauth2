package com.spd.controller;

import com.spd.dto.UserDTO;
import com.spd.entity.User;
import com.spd.repository.UserRepository;
import com.spd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        UserService userService = new UserService();
        return userService.getById(1).getFirstName();
    }

    @RequestMapping("/greeting")
    public UserDTO getUser() {
        return new UserDTO();
    }
}
