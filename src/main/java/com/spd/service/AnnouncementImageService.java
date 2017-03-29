package com.spd.service;

import com.spd.entity.AnnouncementImage;
import com.spd.entity.Image;
import com.spd.repository.AnnoucementImageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnnouncementImageService {
    private final AnnoucementImageRepository annoucementImageRepository;
    private final AnnouncementService announcementService;

    public AnnouncementImageService(AnnoucementImageRepository annoucementImageRepository, AnnouncementService announcementService) {
        this.annoucementImageRepository = annoucementImageRepository;
        this.announcementService = announcementService;
    }
    public void saveAnnoucementImage(Image image, String title, Integer id) {
        AnnouncementImage announcementImage = new AnnouncementImage();
        announcementImage.setImage(image);
        announcementImage.setTitle(title);
        announcementImage.setAnnouncement(announcementService.getById(id).get());
        announcementImage.setActive(true);
        annoucementImageRepository.save(announcementImage);
    }

    public void deleteAnnouncementImage(int id) {

        Optional<AnnouncementImage> announcementImage = Optional.ofNullable(annoucementImageRepository.getOne(id));

        if (announcementImage.isPresent()) {
            annoucementImageRepository.delete(id);
        } else {
            //TODO
        }
    }
}