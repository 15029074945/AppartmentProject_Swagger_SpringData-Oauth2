package com.spd.repository;

import com.spd.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {
}
