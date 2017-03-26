package com.spd.controller;

import com.spd.bean.AnnouncementFacilityBean;
import com.spd.service.AnnouncementFacilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/announcement/facility")
@Api("announcement facility")
public class AnnouncementFacilityController {

    private final AnnouncementFacilityService announcementFacilityService;

    @Autowired
    public AnnouncementFacilityController(AnnouncementFacilityService announcementFacilityService) {
        this.announcementFacilityService = announcementFacilityService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "get announcement facility", httpMethod = "GET")
    public List<AnnouncementFacilityBean> getFacility(Authentication authentication, @RequestBody int idAnnouncement) {
        return announcementFacilityService.getFacilities(authentication.getName(), idAnnouncement);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "create announcement facility", httpMethod = "POST")
    public void createFacility(Authentication authentication, @RequestBody List<AnnouncementFacilityBean> announcementFacilityBeans) {
        announcementFacilityService.createFacility(authentication.getName(), announcementFacilityBeans);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "update announcement facility", httpMethod = "PUT")
    public void updateFacility(Authentication authentication, @RequestBody List<AnnouncementFacilityBean> announcementFacilityBeans) {
        announcementFacilityService.updateFacility(authentication.getName(), announcementFacilityBeans);
    }
}
