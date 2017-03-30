package com.spd.service;

import com.spd.bean.FacilityBean;
import com.spd.entity.Facility;
import com.spd.entity.User;
import com.spd.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;
    private final UserService userService;

    @Autowired
    public FacilityService(FacilityRepository facilityRepository, UserService userService) {
        this.facilityRepository = facilityRepository;
        this.userService = userService;
    }

    public Facility getFacilityByTitle(String title) {
        return facilityRepository.findOneByTitle(title);
    }

    public List<FacilityBean> getAllTitle(String email) {
        User ignore = userService.getByEmail(email);

        List<Facility> facilities = facilityRepository.findAll();
        List<FacilityBean> titles = new ArrayList<>();
        facilities.forEach(f -> {
            String title = f.getTitle();
            FacilityBean facilityBean = new FacilityBean();
            facilityBean.setTitle(title);
            titles.add(facilityBean);
        });
        return titles;
    }

    public List<Facility> getAll() {
        return facilityRepository.findAll();
    }
}
