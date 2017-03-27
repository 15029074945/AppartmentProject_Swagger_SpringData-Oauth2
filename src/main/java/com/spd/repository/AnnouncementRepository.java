package com.spd.repository;

import com.spd.bean.AnnouncementBean;
import com.spd.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

    Optional<Announcement> findOneById(Integer id);

    List<AnnouncementBean> findByUserId(Integer id);

}
