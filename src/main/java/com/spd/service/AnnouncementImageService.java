package com.spd.service;

import com.spd.entity.Announcement;
import com.spd.entity.AnnouncementImage;
import com.spd.entity.Image;
import com.spd.exception.AnnouncementImageException;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.AnnouncementImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementImageService {
    private final AnnouncementImageRepository announcementImageRepository;
    private final AnnouncementService announcementService;
    private final ObjectMapper objectMapper;

    @Autowired
    public AnnouncementImageService(AnnouncementImageRepository announcementImageRepository, AnnouncementService announcementService, ObjectMapper objectMapper) {
        this.announcementImageRepository = announcementImageRepository;
        this.announcementService = announcementService;
        this.objectMapper = objectMapper;
    }
    public void saveAnnouncementImage(Image image, String title, Integer id) {
        AnnouncementImage announcementImage = new AnnouncementImage();
        announcementImage.setImage(image);
        announcementImage.setTitle(title);
        announcementImage.setAnnouncement(announcementService.getById(id));
        announcementImage.setActive(true);
        announcementImageRepository.save(announcementImage);
    }

    public void deleteAnnouncementImage(int id)  {

        Optional<AnnouncementImage> announcementImageOptional = Optional.ofNullable(announcementImageRepository.getOne(id));

        announcementImageOptional
                .map(announcementImage -> {
                    announcementImageRepository.delete(id);
                    return announcementImage;
                })
                .orElseThrow(AnnouncementImageException::new);
    }

    public List<AnnouncementImage> getAnnouncementImages(Integer id) {
        Announcement announcement = announcementService.getById(id);
        return announcement.getAnnouncementImages();
    }
}
