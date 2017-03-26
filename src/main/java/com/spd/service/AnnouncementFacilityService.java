package com.spd.service;

import com.spd.bean.AnnouncementFacilityBean;
import com.spd.entity.Announcement;
import com.spd.entity.AnnouncementFacility;
import com.spd.entity.Facility;
import com.spd.entity.User;
import com.spd.repository.AnnouncementFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.spd.service.ServiceUtil.isAuthenticationUserAnnouncement;

@Service
public class AnnouncementFacilityService {

    private final UserService userService;
    private final AnnouncementService announcementService;
    private final AnnouncementFacilityRepository announcementFacilityRepository;
    private final FacilityService facilityService;

    @Autowired
    public AnnouncementFacilityService(UserService userService, AnnouncementService announcementService, FacilityService facilityService, AnnouncementFacilityRepository announcementFacilityRepository, FacilityService facilityService1) {
        this.userService = userService;
        this.announcementService = announcementService;
        this.announcementFacilityRepository = announcementFacilityRepository;
        this.facilityService = facilityService1;
    }

    public List<AnnouncementFacilityBean> getFacilities(String email, int idAnnouncement) {
        Optional<User> userOptional = userService.getByEmail(email);
        Optional<Announcement> announcementOptional = announcementService.getById(idAnnouncement);
        if (isAuthenticationUserAnnouncement(userOptional, announcementOptional)) {
            List<AnnouncementFacility> announcementFacilities = announcementFacilityRepository.findByAnnouncementId(idAnnouncement);
            List<AnnouncementFacilityBean> announcementFacilityBeans = new ArrayList<>();
            for (AnnouncementFacility announcementFacility : announcementFacilities) {
                AnnouncementFacilityBean announcementFacilityBean = new AnnouncementFacilityBean();
                announcementFacilityBean.setIdAnnouncement(idAnnouncement);
                announcementFacilityBean.setEnable(announcementFacility.getEnabled());
                announcementFacilityBean.setTitle(announcementFacility.getFacility().getTitle());
                announcementFacilityBeans.add(announcementFacilityBean);
            }
            return announcementFacilityBeans;
        }
        else {
            // TODO
            return new ArrayList<>();
        }
    }

    public void createFacility(String email, List<AnnouncementFacilityBean> announcementFacilityBeans) {
        if (announcementFacilityBeans.size() == 0) {
            return;
        }
        int idAnnouncement = announcementFacilityBeans.get(0).getIdAnnouncement();
        Optional<User> userOptional = userService.getByEmail(email);
        Optional<Announcement> announcementOptional = announcementService.getById(idAnnouncement);
        if (isAuthenticationUserAnnouncement(userOptional, announcementOptional)) {
            for (AnnouncementFacilityBean announcementFacilityBean : announcementFacilityBeans) {
                AnnouncementFacility announcementFacility = new AnnouncementFacility();
                announcementFacility.setAnnouncement(announcementOptional.get());
                announcementFacility.setEnabled(announcementFacilityBean.getEnable());
                Facility facility = facilityService.getFacilityByTitle(announcementFacilityBean.getTitle());
                announcementFacility.setFacility(facility);
                announcementFacilityRepository.save(announcementFacility);
            }
        }
    }

    public void updateFacility(String email, List<AnnouncementFacilityBean> announcementFacilityBeans) {
        createFacility(email, announcementFacilityBeans);
    }
}
