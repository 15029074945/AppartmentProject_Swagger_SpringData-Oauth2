package com.spd.service;

import com.spd.bean.UserTelephoneBean;
import com.spd.entity.User;
import com.spd.entity.UserTelephone;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.UserTelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public UserTelephoneBean saveUserTelephone(String userEmail, String extraTelephone) {
        Optional<User> userOptional = userService.getByEmail(userEmail);
        if (userOptional.isPresent()) {
            int userId = userOptional.get().getId();
            Optional<UserTelephone> userEmailOptional = userTelephoneRepository.findByUserIdAndTelephone(userId, extraTelephone);
            if (!userEmailOptional.isPresent()) {
                UserTelephone userTelephone = createUserTelephone(userId, extraTelephone);
                return objectMapper.map(userTelephone, UserTelephoneBean.class);
            }
        }
        return new UserTelephoneBean();
    }

    private UserTelephone createUserTelephone(int userId, String email) {
        User user = userService.getById(userId);
        UserTelephone userTelephone= new UserTelephone();
        userTelephone.setTelephone(email);
        userTelephone.setUser(user);
        return userTelephoneRepository.save(userTelephone);
    }

    public void deleteUserTelephone(String email, int id) {
        Optional<UserTelephone> userEmailOptional = userTelephoneRepository
                .findOneById(id);
        userEmailOptional.ifPresent(userEmail -> {
            if (userEmail.getUser().getEmail().equals(email)) {
                userTelephoneRepository.delete(id);
            }
            else {
                // TODO
            }
        });
    }

    public List<UserTelephoneBean> getListByEmail(String email) {
        Optional<User> userOptional = userService.getByEmail(email);
        if (userOptional.isPresent()) {
            List<UserTelephone> userEmails = userTelephoneRepository.findByUserId(userOptional.get().getId());
            return objectMapper.mapAsList(userEmails, UserTelephoneBean.class);
        }
        else {
            return new ArrayList<>();
        }
    }

    public void updateUserTelephone(String email, UserTelephoneBean userTelephoneBean) {
        Optional<User> userOptional = userService.getByEmail(email);
        Optional<UserTelephone> userTelephoneOptional = userTelephoneRepository.findOneById(userTelephoneBean.getId());
        if (userOptional.isPresent() && userTelephoneOptional.isPresent() &&
                userOptional.get().getId().equals(userTelephoneOptional.get().getUser().getId())) {
            UserTelephone userTelephone = userTelephoneOptional.get();
            userTelephone.setTelephone(userTelephoneBean.getTelephone());
            userTelephoneRepository.save(userTelephone);
        }
    }

    /*public UserTelephone getUserTelephone(String extraTelephone) {
        Optional<UserTelephone> userEmailOptional = userTelephoneRepository.findOneByEmail(email);
        if (userEmailOptional.isPresent()) {
            return userEmailOptional.get();
        }
        throw new NullPointerException("Not found user with email");
    }*/
}
