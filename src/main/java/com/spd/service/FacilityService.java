package com.spd.service;

import com.spd.bean.FacilityBean;
import com.spd.entity.Facility;
import com.spd.entity.User;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.FacilityRepository;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    private final ObjectMapper objectMapper;
    private final FacilityRepository facilityRepository;
    private final UserService userService;

    @Autowired
    public FacilityService(ObjectMapper objectMapper, FacilityRepository facilityRepository, UserService userService) {
        this.objectMapper = objectMapper;
        this.facilityRepository = facilityRepository;
        this.userService = userService;
    }

    public Facility getFacilityByTitle(String title) {
        return facilityRepository.findOneByTitle(title);
    }

    public List<FacilityBean> getAllTitleByEmailUser(String email) {
        Optional<User> userOptional = userService.getByEmail(email);
        if (userOptional.isPresent()) {
            List<Facility> facilities = facilityRepository.findAll();
            List<String> titles = new ArrayList<>();
            facilities.forEach(f -> titles.add(f.getTitle()));
            return objectMapper.mapAsList(titles, FacilityBean.class);
        }
        // TODO
        return new ArrayList<>();
    }
}
