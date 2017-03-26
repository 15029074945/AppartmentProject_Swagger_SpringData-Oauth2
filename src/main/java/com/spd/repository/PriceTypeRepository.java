package com.spd.repository;

import com.spd.entity.PriceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceTypeRepository extends JpaRepository<PriceType, Integer> {

    PriceType findOneByType(String type);

}
