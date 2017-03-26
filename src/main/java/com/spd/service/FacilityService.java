package com.spd.service;

import com.spd.entity.Facility;
import com.spd.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;

    @Autowired
    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public Facility getFacilityByTitle(String title) {
        return facilityRepository.findOneByTitle(title);
    }
}
