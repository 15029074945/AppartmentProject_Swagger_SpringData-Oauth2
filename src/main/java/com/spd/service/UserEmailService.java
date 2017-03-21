package com.spd.service;

import com.spd.bean.UserEmailBean;
import com.spd.entity.User;
import com.spd.entity.UserEmail;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.UserEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void saveUserEmail(int userId, String email) {
        Optional<UserEmail> userEmailOptional = userEmailRepository.findByUserIdAndEmail(userId, email);
        if (!userEmailOptional.isPresent()) {
            createUserEmail(userId, email);
        }
    }

    private void createUserEmail(int userId, String email) {
        User user = userService.getById(userId);
        UserEmail userEmail = new UserEmail();
        userEmail.setEmail(email);
        userEmail.setUser(user);
        userEmailRepository.save(userEmail);
    }

    public void deleteUserEmail(int idEmail) {
        userEmailRepository
                .findOneById(idEmail)
                .ifPresent(userEmailRepository::delete);
    }

    public List<UserEmailBean> getListByUserId(int id) {
        List<UserEmail> userEmails = userEmailRepository.findByUserId(id);
        return objectMapper.mapAsList(userEmails, UserEmailBean.class);
    }

    public UserEmail getUserEmail(String email) {
        Optional<UserEmail> userEmailOptional = userEmailRepository.findOneByEmail(email);
        if (userEmailOptional.isPresent()) {
            return userEmailOptional.get();
        }
        throw new NullPointerException("Not found user with email");
    }
}
