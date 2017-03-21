package com.spd.repository;

import com.spd.entity.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEmailRepository extends JpaRepository<UserEmail, Integer> {

    Optional<UserEmail> findByUserIdAndEmail(int userId, String email);

    Optional<UserEmail> findOneById(int id);

    List<UserEmail> findByUserId(int id);

    Optional<UserEmail> findOneByEmail(String email);
}
