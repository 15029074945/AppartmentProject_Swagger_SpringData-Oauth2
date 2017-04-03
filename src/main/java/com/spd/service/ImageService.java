package com.spd.service;

import com.spd.entity.Image;
import com.spd.exception.ImageException;
import com.spd.repository.ImageRepository;
import com.spd.validator.FileValidator;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image saveImage(MultipartFile imageFile)
            throws IOException, ImageException
    {
        String mimeType;
        FileValidator fileValidator = new FileValidator();

        try {
            MagicMatch match = Magic.getMagicMatch(imageFile.getBytes());
            mimeType = match.getMimeType();
        } catch (Exception e) {
            mimeType = "";
        }

        fileValidator.validate(mimeType, imageFile);

        Image image = new Image();
        image.setMimeType(mimeType);
        image.setData(imageFile.getBytes());
        imageRepository.save(image);
        return image;
    }

    public Image getImage(int id) {
        return imageRepository.findOne(id);
    }
}
