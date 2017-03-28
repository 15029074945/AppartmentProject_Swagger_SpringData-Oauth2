package com.spd.service;

import com.spd.repository.AnnoucementImageRepository;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementImageService {
    private final AnnoucementImageRepository annoucementImageRepository;

    public AnnouncementImageService(AnnoucementImageRepository annoucementImageRepository) {
        this.annoucementImageRepository = annoucementImageRepository;
    }

    public void deleteAnnouncementImage(int id) {
        annoucementImageRepository.delete(id);
    }
}
