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

    public void saveUserTelephone(int userId, String telephone) {
        Optional<UserTelephone> userEmailOptional = userTelephoneRepository.findByUserIdAndTelephone(userId, telephone);
        if (!userEmailOptional.isPresent()) {
            createUserTelephone(userId, telephone);
        }
    }

    private void createUserTelephone(int userId, String email) {
        User user = userService.getById(userId);
        UserTelephone userTelephone= new UserTelephone();
        userTelephone.setTelephone(email);
        userTelephone.setUser(user);
        userTelephoneRepository.save(userTelephone);
    }

    public void deleteUserTelephone(int id) {
        userTelephoneRepository
                .findOneById(id)
                .ifPresent(userTelephoneRepository::delete);
    }

    public List<UserTelephoneBean> getListByUserId(int id) {
        List<UserTelephone> userTelephones = userTelephoneRepository.findByUserId(id);
        return objectMapper.mapAsList(userTelephones, UserTelephoneBean.class);
    }
}
