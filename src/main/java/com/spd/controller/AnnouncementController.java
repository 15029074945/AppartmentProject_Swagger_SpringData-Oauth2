package com.spd.controller;

import com.spd.bean.AnnouncementBean;
import com.spd.bean.AnnouncementIdentifiedBean;
import com.spd.exception.AuthenticationUserException;
import com.spd.service.AnnouncementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/announcements")
@Api(value = "announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "get announcements list", httpMethod = "GET")
    public List<AnnouncementIdentifiedBean> getAnnouncements(Authentication authentication) {
        Optional.ofNullable(authentication)
                .orElseThrow(() -> new AuthenticationUserException("User not authentication"));
        return announcementService.getAnnouncementsByUserEmail(authentication.getName());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "get announcement", httpMethod = "GET")
    public AnnouncementIdentifiedBean getAnnouncement(Authentication authentication, @PathVariable("id") int id) {
        return announcementService.getAnnouncementById(authentication.getName(), id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "create announcement", httpMethod = "POST")
    public AnnouncementIdentifiedBean create(Authentication authentication, @RequestBody AnnouncementBean announcementBean) {
        return announcementService.createAnnouncement(authentication.getName(), announcementBean);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "update announcement", httpMethod = "PUT")
    public void update(Authentication authentication, @RequestBody AnnouncementIdentifiedBean announcementIdentifiedBean) {
        announcementService.updateAnnouncement(authentication.getName(), announcementIdentifiedBean);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete announcement", httpMethod = "DELETE")
    public void delete(Authentication authentication, @PathVariable("id") int id) {
        announcementService.deleteAnnouncement(authentication.getName(), id);
    }

}
