package com.spd.controller;

import com.spd.bean.AnnouncementBean;
import com.spd.entity.Announcement;
import com.spd.mapper.ObjectMapper;
import com.spd.service.AnnouncementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/announcements")
@Api(value = "announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final ObjectMapper objectMapper;

    @Autowired
    public AnnouncementController(ObjectMapper objectMapper, AnnouncementService announcementService) {
        this.objectMapper = objectMapper;
        this.announcementService = announcementService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "create announcement", httpMethod = "POST")
    public ResponseEntity<Integer> createAnnouncement(@RequestBody AnnouncementBean announcementBean) {
        Announcement announcement = objectMapper.map(announcementBean, Announcement.class);
        int idAnnouncement = announcementService.saveAnnouncementReturnId(announcement);
        return new ResponseEntity<>(idAnnouncement, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete announcement", httpMethod = "DELETE")
    public void deleteAnnouncement(@PathVariable("id") int id) {
        announcementService.deleteAnnouncement(id);
    }

}
