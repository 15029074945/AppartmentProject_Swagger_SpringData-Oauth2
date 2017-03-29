package com.spd.service;

import com.spd.bean.FacilityBean;
import com.spd.entity.Announcement;
import com.spd.entity.AnnouncementFacility;
import com.spd.entity.Facility;
import com.spd.entity.User;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.AnnouncementFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.spd.util.ServiceUtil.isAuthenticationUserAnnouncement;

@Service
public class AnnouncementFacilityService {

    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final AnnouncementService announcementService;
    private final AnnouncementFacilityRepository announcementFacilityRepository;
    private final FacilityService facilityService;

    @Autowired
    public AnnouncementFacilityService(UserService userService, AnnouncementService announcementService, FacilityService facilityService, ObjectMapper objectMapper, AnnouncementFacilityRepository announcementFacilityRepository, FacilityService facilityService1) {
        this.userService = userService;
        this.announcementService = announcementService;
        this.objectMapper = objectMapper;
        this.announcementFacilityRepository = announcementFacilityRepository;
        this.facilityService = facilityService1;
    }

    public List<FacilityBean> getFacilities(String email, int idAnnouncement) {
        Optional<User> userOptional = userService.getByEmail(email);
        Optional<Announcement> announcementOptional = announcementService.getById(idAnnouncement);
        if (isAuthenticationUserAnnouncement(userOptional, announcementOptional)) {
            List<AnnouncementFacility> announcementFacilities = announcementFacilityRepository.findByAnnouncementId(idAnnouncement);
            List<FacilityBean> facilityBeans = new ArrayList<>();
            announcementFacilities
                    .forEach(af -> facilityBeans.add(new FacilityBean(af.getFacility().getTitle())));
            return facilityBeans;
        }
        else {
            // TODO
            return new ArrayList<>();
        }
    }

    @Transactional
    public void createFacility(String email, List<FacilityBean> facilityBeans, int idAnnouncement) {
        Optional<User> userOptional = userService.getByEmail(email);
        Optional<Announcement> announcementOptional = announcementService.getById(idAnnouncement);
        if (isAuthenticationUserAnnouncement(userOptional, announcementOptional)) {

            List<AnnouncementFacility> announcementFacilitiesOld = announcementFacilityRepository.findByAnnouncementId(idAnnouncement);
            announcementFacilityRepository.delete(announcementFacilitiesOld);

            List<Facility> facilities =
                    objectMapper.mapAsList(facilityBeans, Facility.class);
            List<AnnouncementFacility> announcementFacilitiesNew = new ArrayList<>();
            facilities.forEach(f -> {
                AnnouncementFacility af = new AnnouncementFacility();
                af.setEnabled(true);
                int idFacility = facilityService.getFacilityByTitle(f.getTitle()).getId();
                f.setId(idFacility);
                af.setFacility(f);
                Announcement announcement = announcementOptional.get();
                af.setAnnouncement(announcement);
                announcementFacilitiesNew.add(af);
            });
            announcementFacilityRepository.save(announcementFacilitiesNew);
        }
    }

    public void updateFacility(String email, List<FacilityBean> facilityBeans, int idAnnouncement) {
        createFacility(email, facilityBeans, idAnnouncement);
    }
}
