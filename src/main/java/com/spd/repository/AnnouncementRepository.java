package com.spd.repository;

import com.spd.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

    Optional<Announcement> findOneById(Integer id);

    List<Announcement> findByUserId(Integer id);

    List<Announcement> findByUserIdAndActiveTrue(Integer id);

    Optional<Announcement> findOneByIdAndUserId(Integer id, Integer userId);

}
