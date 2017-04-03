package com.spd.controller;

import com.spd.bean.AnnouncementImageBean;
import com.spd.service.AnnouncementImageService;
import com.spd.service.AnnouncementService;
import com.spd.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/announcements/{id}/images")
@Api(value = "announcement images")
public class AnnouncementImageController {

    private final ImageService imageService;
    private final AnnouncementService announcementService;
    private final AnnouncementImageService announcementImageService;
    @Autowired
    public AnnouncementImageController(ImageService imageService, AnnouncementService announcementService, AnnouncementImageService announcementImageService) {
        this.imageService = imageService;
        this.announcementService = announcementService;
        this.announcementImageService = announcementImageService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "create announcement image ", httpMethod = "POST")
    public void saveAnnouncementImage(@RequestBody AnnouncementImageBean announcementImageBean, @PathVariable Integer id){
        announcementImageService.saveAnnoucementImage(imageService.getImage(announcementImageBean.getId()),
                announcementImageBean.getTitle(),id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete announcement picture", httpMethod = "DELETE")
    public void delete(@RequestBody AnnouncementImageBean announcementImageBean)  {
        announcementImageService.deleteAnnouncementImage(announcementImageBean.getId());
    }
}
