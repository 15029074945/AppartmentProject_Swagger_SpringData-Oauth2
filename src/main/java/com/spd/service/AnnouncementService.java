package com.spd.service;

import com.spd.entity.Announcement;
import com.spd.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public Integer saveAnnouncementReturnId(Announcement announcement) {
        Announcement newAnnouncement = announcementRepository.save(announcement);
        return newAnnouncement.getId();
    }

    public void deleteAnnouncement(int idAnnouncement) {
        announcementRepository.delete(idAnnouncement);
    }
}
