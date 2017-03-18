package com.spd.repository;

import com.spd.entity.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEmailRepository extends JpaRepository<UserEmail, Integer> {

    UserEmail findByUserIdAndEmail(int userId, String email);

}
