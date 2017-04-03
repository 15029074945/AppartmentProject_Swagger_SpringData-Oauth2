package com.spd.service;

import com.spd.bean.ImageBean;
import com.spd.entity.Image;
import com.spd.exception.ImageException;
import com.spd.mapper.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    @Autowired
    public ImageService(ImageRepository imageRepository, ObjectMapper objectMapper) {
        this.imageRepository = imageRepository;
        this.objectMapper = objectMapper;
    }

    public Image saveImage(MultipartFile imageFile) {
        String mimeType;
        FileValidator fileValidator = new FileValidator();

        try {
            MagicMatch match = Magic.getMagicMatch(imageFile.getBytes());
            mimeType = match.getMimeType();
        } catch (Exception e) {
            mimeType = "";
        }

        try {
            fileValidator.validate(mimeType, imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ImageException e) {
            e.printStackTrace();
        }

        Image image = new Image();
        image.setMimeType(mimeType);
        try {
            image.setData(imageFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageRepository.save(image);
        //return objectMapper.map(image, ImageBean.class);
        return image;
    }

    public Image getImage(int id) {
        return imageRepository.findOne(id);
    }
}
