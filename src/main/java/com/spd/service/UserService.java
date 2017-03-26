package com.spd.service;

import com.spd.bean.UserInformationBean;
import com.spd.bean.UserRegistrationBean;
import com.spd.entity.Image;
import com.spd.entity.User;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ImageService imageService;

    @Autowired
    public UserService(ObjectMapper objectMapper, UserRepository userRepository, ImageService imageService) {
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
        this.imageService = imageService;
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
        )).orElseThrow(() -> new UsernameNotFoundException("Not user TODO"));
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

    public void deleteUser(String email) {
        Optional<User> userOptional = userRepository.findOneByEmail(email);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get().getId());
        }
        else {
            // TODO
        }
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    public void updateUser(Optional<String> emailOptional, UserInformationBean userInformationBean) {
        User newUser = emailOptional
                .flatMap(userRepository::findOneByEmail)
                .map(user -> updateUserWithUserRegistrationBean(user, userInformationBean))
                .orElseGet(() -> objectMapper.map(userInformationBean, User.class));
        saveUser(newUser);
    }

    public void saveUser(Optional<String> emailOptional, UserRegistrationBean userRegistrationBean) {
        Optional<User> userOptional = emailOptional
                .flatMap(userRepository::findOneByEmail);
        if (userOptional.isPresent()) {
            saveUser(userOptional.get());
        }
        else {
            // TODO
        }
    }

    private User updateUserWithUserRegistrationBean(User user, UserInformationBean userInformationBean) {
        user.setFirstName(userInformationBean.getFirstName());
        user.setLastName(userInformationBean.getLastName());
        user.setPassword(userInformationBean.getPassword());
        return user;
    }

    public void saveUser(UserRegistrationBean userRegistrationBean) {
        User user = objectMapper.map(userRegistrationBean, User.class);
        saveUser(user);
    }


    public void setImage(int id, int imageId) {
        User user = getById(id);
        Image image = imageService.getImage(imageId);
        user.setImage(image);
        userRepository.save(user);
    }
}
