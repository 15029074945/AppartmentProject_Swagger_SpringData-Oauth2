package com.spd.repository;

import com.spd.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Integer> {

    Optional<UserToken> findOneByToken(String token);

    Optional<UserToken> findOneByUserId(Integer id);

}
