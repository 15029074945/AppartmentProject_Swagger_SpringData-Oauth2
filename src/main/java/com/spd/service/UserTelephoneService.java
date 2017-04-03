package com.spd.service;

import com.spd.bean.UserTelephoneBean;
import com.spd.entity.User;
import com.spd.entity.UserTelephone;
import com.spd.exception.AlreadyHaveUserTelephone;
import com.spd.exception.NoSuchUserTelephoneException;
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

    public UserTelephoneBean saveUserTelephone(String userEmail, String extraTelephone) {
        User user = userService.getByEmail(userEmail);
        int userId = user.getId();
        Optional<UserTelephone> userEmailOptional = userTelephoneRepository.findOneByUserIdAndTelephone(userId, extraTelephone);
        if (userEmailOptional.isPresent()) {
            throw new AlreadyHaveUserTelephone("Already have this extra telephone");
        }
        else {
            UserTelephone userTelephone = createUserTelephone(user, extraTelephone);
            return objectMapper.map(userTelephone, UserTelephoneBean.class);
        }
    }

    private UserTelephone createUserTelephone(User user, String email) {
        UserTelephone userTelephone= new UserTelephone();
        userTelephone.setTelephone(email);
        userTelephone.setUser(user);
        return userTelephoneRepository.save(userTelephone);
    }

    public void deleteUserTelephone(String email, int id) {
        User user = userService.getByEmail(email);
        Optional<UserTelephone> userEmailOptional = userTelephoneRepository.findOneByUserIdAndId(user.getId(), id);
        userEmailOptional.map(userEmail -> {
            userTelephoneRepository.delete(id);
            return Optional.of(userEmail);
        })
        .orElseThrow(() -> new NoSuchUserTelephoneException("No such user telephone"));
    }

    public List<UserTelephoneBean> getListByEmail(String email) {
        User user = userService.getByEmail(email);
        List<UserTelephone> userEmails = userTelephoneRepository.findByUserId(user.getId());
        return objectMapper.mapAsList(userEmails, UserTelephoneBean.class);
    }

    public void updateUserTelephone(String email, UserTelephoneBean userTelephoneBean) {
        User user = userService.getByEmail(email);
        Optional<UserTelephone> userEmailOptional = userTelephoneRepository.findOneByUserIdAndId(user.getId(), userTelephoneBean.getId());
        userEmailOptional.flatMap(userEmail -> {
            userEmail.setTelephone(userTelephoneBean.getTelephone());
            userTelephoneRepository.save(userEmail);
            return Optional.of(userEmail);
        })
        .orElseThrow(() -> new NoSuchUserTelephoneException("No such user email"));
    }

}
