package com.spd.service;

import com.spd.entity.User;
import com.spd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getById(int id) {
        return userRepository.getOne(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findByEmail(String email) {

        List<User> users = (List<User>) userRepository.findByEmail(email);

        if(users.size() !=  1){
            return null;
        }
        return users.get(0);
    }
}
