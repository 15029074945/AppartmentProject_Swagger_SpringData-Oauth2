package com.spd.service;

import com.spd.bean.UserInformationBean;
import com.spd.bean.UserRegistrationBean;
import com.spd.entity.Image;
import com.spd.entity.User;
import com.spd.entity.UserToken;
import com.spd.exception.NoSuchUserException;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Optional;

@Service
public class UserService {

    private final ObjectMapper objectMapper;
    private final UserTokenService userTokenService;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final ImageService imageService;

    @Autowired
    public UserService(ObjectMapper objectMapper, UserTokenService userTokenService, EmailService emailService, UserRepository userRepository, ImageService imageService) {
        this.objectMapper = objectMapper;
        this.userTokenService = userTokenService;
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    public User createUser(UserRegistrationBean userRegistrationBean) {
        User user = objectMapper.map(userRegistrationBean, User.class);
        user.setStatus(false);
        return saveUser(user);
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

    public void verification(String token) {
        Optional<UserToken> userTokenOptional = userTokenService.getUserTokenByToken(token);
        if (userTokenOptional.isPresent()) {
            User user = userTokenOptional.get().getUser();
            user.setStatus(true);
            userRepository.save(user);
            userTokenService.deleteToken(userTokenOptional.get());
        }
        else {
            // TODO
        }
    }

    public void updateUser(Optional<String> emailOptional, UserInformationBean userInformationBean) {
        User newUser = emailOptional
                .flatMap(userRepository::findOneByEmailAndActiveTrue)
                .map(user -> updateUserWithUserRegistrationBean(user, userInformationBean))
                .orElseGet(() -> objectMapper.map(userInformationBean, User.class));
        saveUser(newUser);
    }

    public User getById(int id) {
        return userRepository.getOne(id);
    }

    public void updateUser(User user) {
        userRepository.save(user);
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

    public void registration(User user) throws MessagingException {
        String token = userTokenService.createToken();
        userTokenService.saveToken(user, token);

        String link = emailService.createLink(token);
        emailService.sendMail(user.getEmail(), link);
    }

    public void repeatSendEmail(User user) {
        Optional<UserToken> userTokenOptional = userTokenService.getUserTokenByUser(user);
        if (userTokenOptional.isPresent()) {
            String token = userTokenOptional.get().getToken();
            String link = emailService.createLink(token);
            try {
                emailService.sendMail(user.getEmail(), link);
            } catch (MessagingException ignored) {
                // TODO
            }
        }
        else {
            try {
                registration(user);
            } catch (MessagingException ignored) {
                // TODO
            }
        }
    }
}
