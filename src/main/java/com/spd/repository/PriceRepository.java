package com.spd.repository;

import com.spd.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price,Integer> {

    List<Price> findByAnnouncementIdAndActiveTrue(int idAnnouncement);

    Optional<Price> findOneByTypeAndAnnouncementIdAndActiveTrue(String type, Integer id);

}
