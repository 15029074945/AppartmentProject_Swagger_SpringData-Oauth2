package com.spd.service;

import com.spd.bean.UserEmailBean;
import com.spd.entity.User;
import com.spd.entity.UserEmail;
import com.spd.exception.AlreadyHaveUserEmail;
import com.spd.exception.NoSuchUserEmailException;
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

    public UserEmailBean saveUserEmail(String email, String extraEmail) {
        User user = userService.getByEmail(email);

        Optional<UserEmail> userEmailOptional = userEmailRepository.findOneByUserIdAndEmail(user.getId(), extraEmail);
        if (userEmailOptional.isPresent()) {
            throw new AlreadyHaveUserEmail("Already have this extra email");
        }
        else {
            UserEmail userEmail = createUserEmail(user, extraEmail);
            return objectMapper.map(userEmail, UserEmailBean.class);
        }
    }

    private UserEmail createUserEmail(User user, String email) {
        UserEmail userEmail = new UserEmail();
        userEmail.setEmail(email);
        userEmail.setUser(user);
        return userEmailRepository.save(userEmail);
    }

    public void deleteUserEmail(String email, int id) {
        User user = userService.getByEmail(email);
        Optional<UserEmail> userEmailOptional = userEmailRepository.findOneByUserIdAndId(user.getId(), id);
        userEmailOptional.map(userEmail -> {
            userEmailRepository.delete(id);
            return Optional.of(userEmail);
        })
        .orElseThrow(() -> new NoSuchUserEmailException("No such user email"));
    }

    public List<UserEmailBean> getListByUserEmail(String email) {
        User user = userService.getByEmail(email);
        List<UserEmail> userEmails = userEmailRepository.findByUserId(user.getId());
        return objectMapper.mapAsList(userEmails, UserEmailBean.class);
    }

    public void updateUserEmail(String email, UserEmailBean userEmailBean) {
        User user = userService.getByEmail(email);
        Optional<UserEmail> userEmailOptional = userEmailRepository.findOneByUserIdAndId(user.getId(), userEmailBean.getId());
        userEmailOptional.flatMap(userEmail -> {
            userEmail.setEmail(userEmailBean.getEmail());
            userEmailRepository.save(userEmail);
            return Optional.of(userEmail);
        })
        .orElseThrow(() -> new NoSuchUserEmailException("No such user email"));
    }
}
