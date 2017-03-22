package com.spd.controller;

import com.spd.bean.AnnouncementRequestBean;
import com.spd.bean.AnnouncementResponseBean;
import com.spd.entity.Announcement;
import com.spd.mapper.ObjectMapper;
import com.spd.service.AnnouncementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.util.Optional;

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
    public AnnouncementResponseBean createOrUpdateAnnouncement(Authentication authentication, @RequestBody AnnouncementRequestBean announcementRequestBean) {
        Announcement announcement = objectMapper.map(announcementRequestBean, Announcement.class);

        //announcement = announcementService.saveAnnouncement(announcement);
        announcementService
                .saveAnnouncement(Optional
                        .ofNullable(authentication)
                        .map(Principal::getName), announcementRequestBean);

        return objectMapper.map(announcement, AnnouncementResponseBean.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete announcement", httpMethod = "DELETE")
    public void deleteAnnouncement(@PathVariable("id") int id) {
        announcementService.deleteAnnouncement(id);
    }

}
