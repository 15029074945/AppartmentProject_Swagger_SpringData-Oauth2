package com.spd.repository;


import com.spd.entity.AnnouncementImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnoucementImageRepository extends JpaRepository<AnnouncementImage,Integer> {
}
