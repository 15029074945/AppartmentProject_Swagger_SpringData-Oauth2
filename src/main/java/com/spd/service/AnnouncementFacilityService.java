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
        User user = userService.getByEmail(email);
        Announcement announcement = announcementService.getByIdAndUserId(idAnnouncement, user.getId());
        return getFacilityBeans(announcement);
    }

    public List<FacilityBean> getFacilityBeans(Announcement announcement) {
        List<AnnouncementFacility> announcementFacilities =
                announcementFacilityRepository.findByAnnouncementId(announcement.getId());

        List<FacilityBean> facilityBeans = new ArrayList<>();
        announcementFacilities
                .forEach(af -> {
                    String title = af.getFacility().getTitle();
                    FacilityBean facilityBean = new FacilityBean();
                    facilityBean.setTitle(title);
                    facilityBeans.add(facilityBean);
                });

        return facilityBeans;
    }

    @Transactional
    public void createFacility(String email, List<FacilityBean> facilityBeans, int idAnnouncement) {
        User user = userService.getByEmail(email);
        Announcement announcement = announcementService.getByIdAndUserId(idAnnouncement, user.getId());

        deleteAllAnnouncementFacilityByIdAnnouncement(idAnnouncement);

        addAllAnnouncementFacilityToAnnouncement(facilityBeans, announcement);
    }

    private void deleteAllAnnouncementFacilityByIdAnnouncement(int idAnnouncement) {
        List<AnnouncementFacility> announcementFacilitiesOld = announcementFacilityRepository.findByAnnouncementId(idAnnouncement);
        announcementFacilityRepository.delete(announcementFacilitiesOld);
    }

    private void addAllAnnouncementFacilityToAnnouncement(List<FacilityBean> facilityBeans, Announcement announcement) {
        List<Facility> facilities = objectMapper.mapAsList(facilityBeans, Facility.class);

        List<AnnouncementFacility> announcementFacilitiesNew = new ArrayList<>();

        facilities.forEach(f -> {
            AnnouncementFacility announcementFacility = new AnnouncementFacility();
            announcementFacility.setEnabled(true);
            String title = f.getTitle();
            Facility facilityByTitle = facilityService.getFacilityByTitle(title);
            int idFacility = facilityByTitle.getId();
            f.setId(idFacility);
            announcementFacility.setFacility(f);
            announcementFacility.setAnnouncement(announcement);
            announcementFacilitiesNew.add(announcementFacility);
        });
        announcementFacilityRepository.save(announcementFacilitiesNew);
    }

    public void updateFacility(String email, List<FacilityBean> facilityBeans, int idAnnouncement) {
        createFacility(email, facilityBeans, idAnnouncement);
    }

    public void deleteFacility(String email, int idAnnouncement) {
        User user = userService.getByEmail(email);
        Announcement announcement = announcementService.getByIdAndUserId(idAnnouncement, user.getId());

        deleteAllAnnouncementFacilityByIdAnnouncement(announcement.getId());
    }
}
