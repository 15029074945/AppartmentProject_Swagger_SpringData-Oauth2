package com.spd.service;

import com.spd.bean.UserRegistrationBean;
import com.spd.entity.User;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserService(ObjectMapper objectMapper, UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findOneByEmail(email);

        return userOptional.map(user ->
                new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getActive(),
                true,
                true,
                true,
                new ArrayList<>()
        )).orElse(null);
    }

    public User getById(int id) {
        return userRepository.getOne(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public boolean deleteUser(int id) {
        try {
            userRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    public void saveUser(Optional<String> emailOptional, UserRegistrationBean userRegistrationBean) {
        User newUser = emailOptional
                .flatMap(userRepository::findOneByEmail)
                .map(user -> updateUserWithUserRegistrationBean(user, userRegistrationBean))
                .orElseGet(() -> objectMapper.map(userRegistrationBean, User.class));
        saveUser(newUser);
    }

    private User updateUserWithUserRegistrationBean(User user, UserRegistrationBean userRegistrationBean) {
        user.setFirstName(userRegistrationBean.getFirstName());
        user.setLastName(userRegistrationBean.getLastName());
        user.setPassword(userRegistrationBean.getPassword());
        return user;
    }
}
