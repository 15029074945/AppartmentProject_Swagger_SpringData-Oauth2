package com.spd.service;

import com.spd.entity.Image;
import com.spd.entity.User;
import com.spd.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final UserService userService;
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(UserService userService, ImageRepository imageRepository) {
        this.userService = userService;
        this.imageRepository = imageRepository;
    }

    public void saveImage(int id, byte[] data) {
        Image image = new Image();
        image.setData(data);
        imageRepository.save(image);
        User user = userService.getById(id);
        user.setImage(image);
        userService.saveUser(user);
    }
}
