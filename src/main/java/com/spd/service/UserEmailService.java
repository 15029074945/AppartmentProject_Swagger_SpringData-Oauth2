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
        Optional<User> userOptional = userService.getByEmail(email);
        if (userOptional.isPresent()) {
            int userId = userOptional.get().getId();
            Optional<UserEmail> userEmailOptional = userEmailRepository.findByUserIdAndEmail(userId, extraEmail);
            if (!userEmailOptional.isPresent()) {
                UserEmail userEmail = createUserEmail(userId, extraEmail);
                return objectMapper.map(userEmail, UserEmailBean.class);
            }
        }
        return new UserEmailBean();
    }

    private UserEmail createUserEmail(int userId, String email) {
        User user = userService.getById(userId);
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
        Optional<User> userOptional = userService.getByEmail(email);
        if (userOptional.isPresent()) {
            List<UserEmail> userEmails = userEmailRepository.findByUserId(userOptional.get().getId());
            return objectMapper.mapAsList(userEmails, UserEmailBean.class);
        }
        else {
            return new ArrayList<>();
        }
    }

    public UserEmail getUserEmail(String email) {
        Optional<UserEmail> userEmailOptional = userEmailRepository.findOneByEmail(email);
        if (userEmailOptional.isPresent()) {
            return userEmailOptional.get();
        }
        throw new NullPointerException("Not found user with email");
    }

    public void updateUserEmail(String email, UserEmailBean userEmailBean) {
        Optional<User> userOptional = userService.getByEmail(email);
        Optional<UserEmail> userEmailOptional = userEmailRepository.findOneById(userEmailBean.getId());
        if (userOptional.isPresent() && userEmailOptional.isPresent() &&
                userOptional.get().getId().equals(userEmailOptional.get().getUser().getId())) {
            UserEmail userEmail = userEmailOptional.get();
            userEmail.setEmail(userEmailBean.getEmail());
            userEmailRepository.save(userEmail);
        }
    }
}
