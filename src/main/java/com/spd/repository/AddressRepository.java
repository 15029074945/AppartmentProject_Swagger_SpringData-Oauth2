package com.spd.repository;

import com.spd.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository  extends JpaRepository<Address,Integer>{
}
