package com.spd.service;

import com.spd.bean.AnnouncementBean;
import com.spd.entity.Announcement;
import com.spd.entity.User;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.AnnouncementRepository;
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

    public Announcement saveAnnouncement(String email, Announcement announcement) {
        Optional<User> userOptional = userService.getByEmail(email);
        if (userOptional.isPresent()) {
            announcement.setUser(userOptional.get());
            return announcementRepository.save(announcement);
        }
        else {
            // TODO
            return new Announcement();
        }
    }

    public Optional<Announcement> getById(Integer id) {
        return announcementRepository.findOneById(id);
    }

    public void saveAnnouncement(Announcement announcement) {
        announcementRepository.save(announcement);
    }

    public List<AnnouncementBean> getAnnouncementByUserEmail(String email) {
        Optional<User> userOptional = userService.getByEmail(email);
        return announcementRepository.findByUserId(userOptional.get().getId());
    }
}
