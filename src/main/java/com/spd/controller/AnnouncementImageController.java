package com.spd.controller;

import com.spd.service.AnnouncementImageService;
import com.spd.service.AnnouncementService;
import com.spd.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/announcements/images")
@Api(value = "announcement images")
public class AnnouncementImageController {

    private final ImageService imageService;
    private final AnnouncementService announcementService;
    private final AnnouncementImageService announcementImageService;

    public AnnouncementImageController(ImageService imageService, AnnouncementService announcementService, AnnouncementImageService announcementImageService) {
        this.imageService = imageService;
        this.announcementService = announcementService;
        this.announcementImageService = announcementImageService;
    }

    @RequestMapping(value = "{id}/images/{imageId}", method = RequestMethod.POST)
    @ApiOperation(value = "create announcement image ", httpMethod = "POST")
    public void saveAnnouncementImage(Authentication authentication, @RequestBody int imageId){

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete announcement picture", httpMethod = "DELETE")
    public void delete(@RequestBody int id) {
             announcementImageService.deleteAnnouncementImage(id);

    }
}
