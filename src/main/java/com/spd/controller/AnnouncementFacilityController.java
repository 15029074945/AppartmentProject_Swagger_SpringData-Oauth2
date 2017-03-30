package com.spd.controller;

import com.spd.bean.FacilityBean;
import com.spd.service.AnnouncementFacilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/announcements/{idAnnouncement}/facilities")
@Api("announcement facility")
public class AnnouncementFacilityController {

    private final AnnouncementFacilityService announcementFacilityService;

    @Autowired
    public AnnouncementFacilityController(AnnouncementFacilityService announcementFacilityService) {
        this.announcementFacilityService = announcementFacilityService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "get announcement facilities", httpMethod = "GET")
    public List<FacilityBean> getFacilities(Authentication authentication, @PathVariable("idAnnouncement") int idAnnouncement) {
        return announcementFacilityService.getFacilities(authentication.getName(), idAnnouncement);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "create announcement facility", httpMethod = "POST")
    public void createFacility(Authentication authentication, @RequestBody List<FacilityBean> facilityBeans, @PathVariable("idAnnouncement") int idAnnouncement) {
        announcementFacilityService.createFacility(authentication.getName(), facilityBeans, idAnnouncement);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "update announcement facility", httpMethod = "PUT")
    public void updateFacility(Authentication authentication, @RequestBody List<FacilityBean> facilityBeans, @PathVariable("idAnnouncement") int idAnnouncement) {
        announcementFacilityService.updateFacility(authentication.getName(), facilityBeans, idAnnouncement);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete announcement facility", httpMethod = "DELETE")
    public void deleteFacility(Authentication authentication, @PathVariable("idAnnouncement") int idAnnouncement) {
        announcementFacilityService.deleteFacility(authentication.getName(), idAnnouncement);
    }
}
