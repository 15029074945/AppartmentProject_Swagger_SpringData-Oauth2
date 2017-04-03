package com.spd.repository;

import com.spd.entity.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEmailRepository extends JpaRepository<UserEmail, Integer> {

    Optional<UserEmail> findOneByUserIdAndEmail(Integer userId, String email);

    Optional<UserEmail> findOneByUserIdAndId(Integer userId, Integer id);

    List<UserEmail> findByUserId(Integer id);

}
