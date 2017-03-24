package com.spd.service;

import com.spd.bean.UserTelephoneBean;
import com.spd.entity.User;
import com.spd.entity.UserTelephone;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.UserTelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTelephoneService {

    private final ObjectMapper objectMapper;

    private final UserService userService;
    private final UserTelephoneRepository userTelephoneRepository;

    @Autowired
    public UserTelephoneService(ObjectMapper objectMapper, UserService userService, UserTelephoneRepository userTelephoneRepository) {
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.userTelephoneRepository = userTelephoneRepository;
    }

    public UserTelephone saveUserTelephone(String userEmail, String extraTelephone) {
        Optional<User> userOptional = userService.getByEmail(userEmail);
        if (userOptional.isPresent()) {
            int userId = userOptional.get().getId();
            Optional<UserTelephone> userEmailOptional = userTelephoneRepository.findByUserIdAndTelephone(userId, extraTelephone);
            if (!userEmailOptional.isPresent()) {
                return createUserTelephone(userId, extraTelephone);
            }
        }
        return new UserTelephone();
    }

    private UserTelephone createUserTelephone(int userId, String email) {
        User user = userService.getById(userId);
        UserTelephone userTelephone= new UserTelephone();
        userTelephone.setTelephone(email);
        userTelephone.setUser(user);
        return userTelephoneRepository.save(userTelephone);
    }

    public void deleteUserTelephone(int id) {
        userTelephoneRepository
                .findOneById(id)
                .ifPresent(userTelephoneRepository::delete);
    }

    public List<UserTelephoneBean> getListByEmail(String userEmail) {
        int id = userService.getByEmail(userEmail).get().getId();
        List<UserTelephone> userTelephones = userTelephoneRepository.findByUserId(id);
        return objectMapper.mapAsList(userTelephones, UserTelephoneBean.class);
    }

    /*public UserTelephone getUserTelephone(String extraTelephone) {
        Optional<UserTelephone> userEmailOptional = userTelephoneRepository.findOneByEmail(email);
        if (userEmailOptional.isPresent()) {
            return userEmailOptional.get();
        }
        throw new NullPointerException("Not found user with email");
    }*/
}
