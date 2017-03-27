package com.spd.service;

import com.spd.entity.User;
import com.spd.entity.UserToken;
import com.spd.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserTokenService {

    private final UserTokenRepository userTokenRepository;

    @Autowired
    public UserTokenService(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    public String createToken() {
        return UUID.randomUUID().toString();
    }

    public void saveToken(User user, String token) {
        UserToken userToken = new UserToken();
        userToken.setUser(user);
        userToken.setToken(token);
        userTokenRepository.save(userToken);
    }

    public Optional<UserToken> getUserTokenByToken(String token) {
        return userTokenRepository.findOneByToken(token);
    }
}
