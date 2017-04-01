package com.spd.repository;

import com.spd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

     Optional<User> findOneByEmailAndActiveTrue(String email);

     Optional<User> findOneByEmail(String email);

}
