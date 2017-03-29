package com.spd.service;

import com.spd.entity.Facility;
import com.spd.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getAllTitle() {
        List<Facility> facilities = facilityRepository.findAll();
        List<String> titles = new ArrayList<>();
        facilities.forEach(facility -> titles.add(facility.getTitle()));
        return titles;
    }
}
