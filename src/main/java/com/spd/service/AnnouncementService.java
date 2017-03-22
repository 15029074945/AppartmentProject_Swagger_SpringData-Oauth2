package com.spd.service;

import com.spd.bean.AnnouncementRequestBean;
import com.spd.entity.Announcement;
import com.spd.entity.User;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnnouncementService {

    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementService(AnnouncementRepository announcementRepository, ObjectMapper objectMapper, UserService userService) {
        this.announcementRepository = announcementRepository;
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    public Announcement saveAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    public void saveAnnouncement(Optional<String> emailOptional, AnnouncementRequestBean announcementRequestBean) {
        Optional<User> userOptional = emailOptional
                .flatMap(userService::getByEmail);
        Announcement newAnnouncement = userOptional
                .flatMap(user -> announcementRepository.findOneByUserId(user.getId()))
                .map(announcement -> updateAnnouncementWithAnnouncementRequestBean(announcement, announcementRequestBean))
                .orElseGet(() -> objectMapper.map(announcementRequestBean, Announcement.class));
        saveAnnouncement(newAnnouncement);
    }

    private Announcement updateAnnouncementWithAnnouncementRequestBean(Announcement announcement, AnnouncementRequestBean announcementRequestBean) {
        announcement.setTitle(announcementRequestBean.getTitle());
        announcement.setHidden(announcementRequestBean.getHidden());
        announcement.setRoom(announcementRequestBean.getRoom());
        announcement.setShortDescription(announcementRequestBean.getShortDescription());
        announcement.setLivingPlaces(announcementRequestBean.getLivingPlaces());
        return null;
    }

    public void deleteAnnouncement(int idAnnouncement) {
        announcementRepository.delete(idAnnouncement);
    }
}
