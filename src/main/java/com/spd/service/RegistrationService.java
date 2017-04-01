package com.spd.service;

import com.spd.entity.User;
import com.spd.entity.UserToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    private final UserService userService;
    private final EmailService emailService;
    private final UserTokenService userTokenService;

    public RegistrationService(UserService userService, EmailService emailService, UserTokenService userTokenService) {
        this.userService = userService;
        this.emailService = emailService;
        this.userTokenService = userTokenService;
    }

    public void registration(User user) {
        String token = createTokenForUser(user);
        sendRegistrationEmail(user, token);
    }

    private String createTokenForUser(User user) {
        String token = userTokenService.createToken();
        userTokenService.saveToken(user, token);
        return token;
    }

    private void sendRegistrationEmail(User user, String token) {
        String link = emailService.createLink(token);
        emailService.sendMail(user.getEmail(), link);
    }

    public void repeatSendEmail(User user) {
        Optional<UserToken> userTokenOptional = userTokenService.getUserTokenByUser(user);
        if (userTokenOptional.isPresent()) {
            String token = userTokenOptional.get().getToken();
            sendRegistrationEmail(user, token);
        }
        else {
            registration(user);
        }
    }

    public void verification(String token) {
        Optional<UserToken> userTokenOptional = userTokenService.getUserTokenByToken(token);
        if (userTokenOptional.isPresent()) {
            User user = userTokenOptional.get().getUser();
            user.setStatus(true);
            userService.saveUser(user);
            userTokenService.deleteToken(userTokenOptional.get());
        }
        else {
            // TODO
        }
    }

}
