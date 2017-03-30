package com.spd.service;

import com.spd.entity.AnnouncementImage;
import com.spd.entity.Image;
import com.spd.repository.AnnouncementImageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnnouncementImageService {
    private final AnnouncementImageRepository announcementImageRepository;
    private final AnnouncementService announcementService;

    public AnnouncementImageService(AnnouncementImageRepository announcementImageRepository, AnnouncementService announcementService) {
        this.announcementImageRepository = announcementImageRepository;
        this.announcementService = announcementService;
    }
    public void saveAnnoucementImage(Image image, String title, Integer id) {
        AnnouncementImage announcementImage = new AnnouncementImage();
        announcementImage.setImage(image);
        announcementImage.setTitle(title);
        announcementImage.setAnnouncement(announcementService.getById(id).get());
        announcementImage.setActive(true);
        announcementImageRepository.save(announcementImage);
    }

    public void deleteAnnouncementImage(int id) {

        Optional<AnnouncementImage> announcementImage = Optional.ofNullable(announcementImageRepository.getOne(id));

        if (announcementImage.isPresent()) {
            announcementImageRepository.delete(id);
        } else {
            //TODO
        }
    }
}
