package com.spd.service;

import com.spd.entity.Image;
import com.spd.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image saveImage(String mimeType, byte[] data) {
        Image image = new Image();
        image.setMimeType(mimeType);
        image.setData(data);
        imageRepository.save(image);
        return image;
    }

    public Image getImage(int id) {
        return imageRepository.findOne(id);
    }
}
