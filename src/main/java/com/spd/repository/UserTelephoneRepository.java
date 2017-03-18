package com.spd.repository;

import com.spd.entity.UserTelephone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTelephoneRepository extends JpaRepository<UserTelephone, Integer> {

    UserTelephone findByUserIdAndTelephone(int userId, String telephone);

}
