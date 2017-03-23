package com.spd.controller;

import com.spd.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/users/{id}/images")
@Api(value = "users")
public class UserImageController {

    private final ImageService imageService;

    @Autowired
    UserImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ApiOperation(value = "create update user avatar", httpMethod = "POST")
    public void createOrUpdateUserImage(@PathVariable("id") int id,
                                        @RequestParam(value = "images") MultipartFile imageFile) {
        try {
            imageService.saveImage(id, imageFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
