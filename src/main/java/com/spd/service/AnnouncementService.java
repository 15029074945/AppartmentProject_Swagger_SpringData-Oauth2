package com.spd.service;

import com.spd.bean.AnnouncementBean;
import com.spd.entity.Announcement;
import com.spd.entity.User;
import com.spd.exception.AnnouncementException;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.AnnouncementRepository;
import com.spd.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void deleteAnnouncement(String email, int id) {
        Announcement announcement = announcementRepository.findOne(id);
        if (announcement.getUser().getEmail().equals(email)) {
            announcementRepository.delete(id);
        }
        else {
            // TODO
        }
    }

    public AnnouncementBean saveAnnouncement(String email, AnnouncementBean announcementBean) {
        Announcement announcement = objectMapper.map(announcementBean, Announcement.class);

        Optional<User> userOptional = userService.getByEmail(email);
        if (userOptional.isPresent()) {
            announcement.setUser(userOptional.get());
            Announcement newAnnouncement = announcementRepository.save(announcement);
            return objectMapper.map(newAnnouncement, AnnouncementBean.class);
        }
        else {
            // TODO
            return new AnnouncementBean();
        }
    }

    public Optional<Announcement> getById(Integer id) {
        return announcementRepository.findOneById(id);
    }

    public void saveAnnouncement(Announcement announcement) {
        announcementRepository.save(announcement);
    }

    public List<AnnouncementBean> getAnnouncementsByUserEmail(String email) {
        Optional<User> userOptional = userService.getByEmail(email);
        List<Announcement> announcements = announcementRepository.findByUserId(userOptional.get().getId());
        return objectMapper.mapAsList(announcements, AnnouncementBean.class);
    }

    public AnnouncementBean getAnnouncementById(String email, int id) {
        Optional<User> userOptional = userService.getByEmail(email);
        Optional<Announcement> announcementOptional = announcementRepository.findOneById(id);
        if (ServiceUtil.isAuthenticationUserAnnouncement(userOptional, announcementOptional)) {
            return objectMapper.map(announcementOptional.get(), AnnouncementBean.class);
        }
        else {
            // TODO
            throw new AnnouncementException("User does not have such an announcement");
        }
    }
}
