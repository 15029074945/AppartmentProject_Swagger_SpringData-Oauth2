package com.spd.service;

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

    public void deleteAnnouncement(int idAnnouncement) {
        announcementRepository.delete(idAnnouncement);
    }

    public Announcement saveAnnouncement(String email, Announcement announcement) {
        Optional<User> userOptional = userService.getByEmail(email);
        announcement.setUser(userOptional.get());
        return announcementRepository.save(announcement);
    }

    public Optional<Announcement> getById(Integer id) {
        return announcementRepository.findOneByUserId(id);
    }

    public void saveAnnouncement(Announcement announcement) {
        announcementRepository.save(announcement);
    }
}
