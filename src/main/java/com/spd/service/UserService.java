package com.spd.service;

import com.spd.bean.UserInformationBean;
import com.spd.bean.UserRegistrationBean;
import com.spd.entity.Image;
import com.spd.entity.User;
import com.spd.entity.UserToken;
import com.spd.exception.NoSuchUserException;
import com.spd.exception.UserAuthenticationException;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Optional;

@Service
public class UserService {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final ImageService imageService;

    @Autowired
    public UserService(ObjectMapper objectMapper, UserRepository userRepository, ImageService imageService) {
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    public User createUser(UserRegistrationBean userRegistrationBean) {
        String email = userRegistrationBean.getEmail();
        Optional<User> userOptional = userRepository.findOneByEmail(email);
        if (userOptional.isPresent()) {
            if (userOptional.get().getActive()) {
                throw new UserAuthenticationException("User is created");
            }
            else {
                throw new UserAuthenticationException("User is deleted");
            }
        }
        else {
            User user = objectMapper.map(userRegistrationBean, User.class);
            user.setStatus(false);
            return saveUser(user);
        }
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void changePassword(String email, String password) {
        User user = getByEmail(email);
        user.setPassword(password);
        userRepository.save(user);
    }

    public User getByEmail(String email) {
        return userRepository.findOneByEmailAndActiveTrue(email)
                .orElseThrow(() -> new NoSuchUserException("No such user exception"));
    }

    public Optional<User> getUserOptionalByEmail(String email) {
        return userRepository.findOneByEmailAndActiveTrue(email);
    }

    public void updateUser(String email, UserInformationBean userInformationBean) {
        User user = getByEmail(email);
        User newUser = updateUserWithUserRegistrationBean(user, userInformationBean);
        saveUser(newUser);
    }

    public User getById(int id) {
        return userRepository.getOne(id);
    }


    public void deleteUser(String email) {
        User user = getByEmail(email);
        userRepository.delete(user.getId());
    }

    private User updateUserWithUserRegistrationBean(User user, UserInformationBean userInformationBean) {
        user.setFirstName(userInformationBean.getFirstName());
        user.setLastName(userInformationBean.getLastName());
        return user;
    }

    public void setImage(String email, int imageId) {
        User user = getByEmail(email);
        Image image = imageService.getImage(imageId);
        user.setImage(image);
        userRepository.save(user);
    }

}
