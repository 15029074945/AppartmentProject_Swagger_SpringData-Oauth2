package com.spd.service;

import com.spd.bean.AddressBean;
import com.spd.entity.Address;
import com.spd.entity.Announcement;
import com.spd.entity.User;
import com.spd.exception.FilledException;
import com.spd.exception.NoSuchAddressException;
import com.spd.exception.NoSuchAnnouncementException;
import com.spd.exception.NoSuchUserException;
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

    public AddressBean getAddressByAnnouncementId(String email, int idAnnouncement) {
        User user = userService.getByEmail(email);

        Announcement announcement = announcementService.getByIdAndUserId(idAnnouncement, user.getId());

        Address address = announcement.getAddress();
        return objectMapper.map(address, AddressBean.class);
    }

    public AddressBean createAddress(String email, AddressBean addressBean, int idAnnouncement) {
        User user = userService.getByEmail(email);

        Announcement announcement = announcementService.getByIdAndUserId(idAnnouncement, user.getId());

        Address address = objectMapper.map(addressBean, Address.class);

        Optional.ofNullable(announcement.getAddress())
                .ifPresent(ignore -> new FilledException("Announcement has an address"));

        address = addressRepository.save(address);
        announcement.setAddress(address);
        announcementService.saveAnnouncement(announcement);

        return objectMapper.map(address, AddressBean.class);
    }

    public void updateAddress(String email, AddressBean addressBean, int idAnnouncement) {
        User user = userService.getByEmail(email);

        Announcement announcement = announcementService.getByIdAndUserId(idAnnouncement, user.getId());
        Optional.ofNullable(announcement.getAddress())
                .orElseThrow(() -> new NoSuchAddressException("Not such address"));

        Address address = objectMapper.map(addressBean, Address.class);
        address.setId(announcement.getAddress().getId());

        addressRepository.save(address);
    }

    public void deleteAddress(String email, int idAnnouncement) {
        User user = userService.getByEmail(email);

        Announcement announcement = announcementService.getByIdAndUserId(idAnnouncement, user.getId());
        Optional.ofNullable(announcement.getAddress())
                .orElseThrow(() -> new NoSuchAddressException("Not such address"));

        addressRepository.delete(announcement.getAddress());

        announcement.setAddress(null);
        announcementService.saveAnnouncement(announcement);
    }
}
