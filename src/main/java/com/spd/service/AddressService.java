package com.spd.service;

import com.spd.bean.AddressBean;
import com.spd.entity.Address;
import com.spd.entity.Announcement;
import com.spd.entity.User;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AnnouncementService announcementService;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public AddressService(AddressRepository addressRepository, AnnouncementService announcementService, UserService userService, ObjectMapper objectMapper) {
        this.addressRepository = addressRepository;
        this.announcementService = announcementService;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    public AddressBean saveAddress(String email, AddressBean addressBean) {
        int idAnnouncement = addressBean.getIdAnnouncement();
        Address address = objectMapper.map(addressBean, Address.class);
        Optional<User> userOptional = userService.getByEmail(email);
        Optional<Announcement> announcementOptional = announcementService.getById(idAnnouncement);
        System.out.println(announcementOptional.isPresent() + " " +
            userOptional.isPresent() + " " + 111);
        if (announcementOptional.isPresent() && userOptional.isPresent() &&
                userOptional.get().getId().equals(announcementOptional.get().getUser().getId())) {
            System.out.println(111);
            address = addressRepository.save(address);
            announcementOptional.get().setAddress(address);
            announcementService.saveAnnouncement(announcementOptional.get());
            return objectMapper.map(address, AddressBean.class);
        }
        // TODO
        return new AddressBean();
    }

    public void deleteAddress(String email, int idAnnouncement) {
        Optional<User> userOptional = userService.getByEmail(email);
        Optional<Announcement> announcementOptional = announcementService.getById(idAnnouncement);
        if (userOptional.isPresent() && announcementOptional.isPresent() &&
                userOptional.get().getEmail().equals(announcementOptional.get().getUser().getEmail())) {
            addressRepository.delete(announcementOptional.get().getAddress());
        }
    }

    public AddressBean getAddressByAnnouncementId(String email, int idAnnouncement) {
        Optional<Announcement> announcementOptional = announcementService.getById(idAnnouncement);
        Optional<User> userOptional = userService.getByEmail(email);
        if (announcementOptional.isPresent() && userOptional.isPresent()) {
            if (announcementOptional.get().getUser().getEmail().equals(userOptional.get().getEmail())) {
                Address address = announcementOptional.get().getAddress();
                return objectMapper.map(address, AddressBean.class);
            }
            else {
                // TODO
                return new AddressBean();
            }
        }
        else {
            // TODO
            return new AddressBean();
        }
    }
}
