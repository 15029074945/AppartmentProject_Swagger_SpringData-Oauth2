package com.spd.repository;

import com.spd.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price,Integer> {

    List<Price> findByAnnouncementIdAndActiveTrue(int idAnnouncement);

}
