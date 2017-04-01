package com.spd.service;

import com.spd.bean.FacilityBean;
import com.spd.entity.Facility;
import com.spd.entity.User;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public FacilityService(FacilityRepository facilityRepository, ObjectMapper objectMapper) {
        this.facilityRepository = facilityRepository;
        this.objectMapper = objectMapper;
    }

    public Facility getFacilityByTitle(String title) {
        return facilityRepository.findOneByTitle(title);
    }

    public List<FacilityBean> getAllTitleFacilities() {
        List<Facility> facilities = facilityRepository.findAll();
        return objectMapper.mapAsList(facilities, FacilityBean.class);
    }

}
