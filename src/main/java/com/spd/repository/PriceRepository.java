package com.spd.repository;

import com.spd.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PriceRepository extends JpaRepository<Price,Integer> {
}
