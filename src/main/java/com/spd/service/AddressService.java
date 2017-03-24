package com.spd.service;

import com.spd.entity.Address;
import com.spd.entity.Announcement;
import com.spd.entity.User;
import com.spd.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AnnouncementService announcementService;
    private final UserService userService;

    @Autowired
    public AddressService(AddressRepository addressRepository, AnnouncementService announcementService, UserService userService) {
        this.addressRepository = addressRepository;
        this.announcementService = announcementService;
        this.userService = userService;
    }

    public Address saveAddress(String email, Address address, Integer idAnnouncement) {
        Optional<User> userOptional = userService.getByEmail(email);
        Optional<Announcement> announcementOptional = announcementService.getById(idAnnouncement);
        if (announcementOptional.isPresent() && userOptional.isPresent() &&
                userOptional.get().getId() == announcementOptional.get().getUser().getId()) {
            address = addressRepository.save(address);
            announcementOptional.get().setAddress(address);
            announcementService.saveAnnouncement(announcementOptional.get());
            return address;
        }
        return new Address();
    }

    public void deleteAddress(int id) {
        addressRepository.delete(id);
    }
}
