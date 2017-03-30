package com.spd.service;

import com.spd.bean.AnnouncementBean;
import com.spd.bean.AnnouncementIdentifiedBean;
import com.spd.entity.Announcement;
import com.spd.entity.User;
import com.spd.exception.NoSuchAnnouncementException;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<AnnouncementIdentifiedBean> getAnnouncementsByUserEmail(String email) {
        User user = userService.getByEmail(email);
        List<Announcement> announcements = announcementRepository.findByUserIdAndActiveTrue(user.getId());
        return objectMapper.mapAsList(announcements, AnnouncementIdentifiedBean.class);
    }

    public AnnouncementIdentifiedBean getAnnouncementById(String email, int id) {
        User user = userService.getByEmail(email);

        Announcement announcement = getByIdAndUserId(id, user.getId());

        return objectMapper.map(announcement, AnnouncementIdentifiedBean.class);
    }

    public Announcement getById(int id) {
        return announcementRepository.findOneById(id)
                .orElseThrow(() -> new NoSuchAnnouncementException("Not such announcement"));
    }

    public AnnouncementIdentifiedBean createAnnouncement(String email, AnnouncementBean announcementBean) {
        User user = userService.getByEmail(email);

        Announcement announcement = objectMapper.map(announcementBean, Announcement.class);
        announcement.setActive(true);
        announcement.setUser(user);
        Announcement newAnnouncement = announcementRepository.save(announcement);

        return objectMapper.map(newAnnouncement, AnnouncementIdentifiedBean.class);
    }

    public void saveAnnouncement(Announcement announcement) {
        announcementRepository.save(announcement);
    }

    public void updateAnnouncement(String email, AnnouncementIdentifiedBean announcementIdentifiedBean) {
        User user = userService.getByEmail(email);

        Announcement announcement = objectMapper.map(announcementIdentifiedBean, Announcement.class);
        announcement.setActive(true);
        announcement.setUser(user);
        announcementRepository.save(announcement);
    }

    public void deleteAnnouncement(String email, int id) {
        User user = userService.getByEmail(email);

        Announcement announcement = getByIdAndUserId(id, user.getId());

        announcement.setActive(false);

        announcementRepository.save(announcement);
    }

    public Announcement getByIdAndUserId(int id, int idUser) {
        return announcementRepository.findOneByIdAndUserId(id, idUser)
                .orElseThrow(() -> new NoSuchAnnouncementException("User does not have such an announcement"));
    }

}
