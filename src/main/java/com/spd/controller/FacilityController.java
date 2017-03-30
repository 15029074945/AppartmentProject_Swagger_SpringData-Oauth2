package com.spd.controller;

import com.spd.bean.FacilityBean;
import com.spd.service.FacilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/facilities")
@Api(value = "facilities")
public class FacilityController {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "get announcement facilities", httpMethod = "GET")
    public List<FacilityBean> getFacilities(Authentication authentication) {
        return facilityService.getAllTitle(authentication.getName());
    }

}
