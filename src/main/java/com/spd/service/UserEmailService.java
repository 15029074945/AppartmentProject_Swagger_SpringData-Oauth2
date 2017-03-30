package com.spd.service;

import com.spd.bean.UserEmailBean;
import com.spd.entity.User;
import com.spd.entity.UserEmail;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.UserEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserEmailService {

    private final ObjectMapper objectMapper;

    private final UserService userService;
    private final UserEmailRepository userEmailRepository;

    @Autowired
    public UserEmailService(UserEmailRepository userEmailRepository, UserService userService, ObjectMapper objectMapper) {
        this.userEmailRepository = userEmailRepository;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    public UserEmailBean saveUserEmail(String email, String extraEmail) {
        User user = userService.getByEmail(email);

        int userId = user.getId();
        Optional<UserEmail> userEmailOptional = userEmailRepository.findByUserIdAndEmail(userId, extraEmail);
        if (!userEmailOptional.isPresent()) {
            UserEmail userEmail = createUserEmail(user, extraEmail);
            return objectMapper.map(userEmail, UserEmailBean.class);
        }
        else {
            // TODO
            return new UserEmailBean();
        }
    }

    private UserEmail createUserEmail(User user, String email) {
        UserEmail userEmail = new UserEmail();
        userEmail.setEmail(email);
        userEmail.setUser(user);
        return userEmailRepository.save(userEmail);
    }

    public void deleteUserEmail(String email, int id) {
        Optional<UserEmail> userEmailOptional = userEmailRepository
                .findOneById(id);
        userEmailOptional.ifPresent(userEmail -> {
            if (userEmail.getUser().getEmail().equals(email)) {
                userEmailRepository.delete(id);
            }
            else {
                // TODO
            }
        });
    }

    public List<UserEmailBean> getListByUserEmail(String email) {
        User user = userService.getByEmail(email);

        List<UserEmail> userEmails = userEmailRepository.findByUserId(user.getId());
        return objectMapper.mapAsList(userEmails, UserEmailBean.class);
    }

    public UserEmail getUserEmail(String email) {
        Optional<UserEmail> userEmailOptional = userEmailRepository.findOneByEmail(email);
        if (userEmailOptional.isPresent()) {
            return userEmailOptional.get();
        }
        throw new NullPointerException("Not found user with email");
    }

    public void updateUserEmail(String email, UserEmailBean userEmailBean) {
        User user = userService.getByEmail(email);
        Optional<UserEmail> userEmailOptional = userEmailRepository.findOneById(userEmailBean.getId());
        if (userEmailOptional.isPresent() &&
                user.getId().equals(userEmailOptional.get().getUser().getId())) {
            UserEmail userEmail = userEmailOptional.get();
            userEmail.setEmail(userEmailBean.getEmail());
            userEmailRepository.save(userEmail);
        }
    }
}
