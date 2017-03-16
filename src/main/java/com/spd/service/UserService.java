package com.spd.service;

import com.spd.entity.User;
import com.spd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired(required = true)
    private UserRepository userRepository;

    public User getById(int id) {
        return userRepository.findOne(id);
    }
}
