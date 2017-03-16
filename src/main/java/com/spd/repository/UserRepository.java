package com.spd.repository;

import com.spd.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByFirstName(String firstName);

}
