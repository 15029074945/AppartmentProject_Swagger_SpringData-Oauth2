package com.spd.repository;

import com.spd.entity.UserTelephone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTelephoneRepository extends JpaRepository<UserTelephone, Integer> {

    Optional<UserTelephone> findByUserIdAndTelephone(int userId, String telephone);

}
