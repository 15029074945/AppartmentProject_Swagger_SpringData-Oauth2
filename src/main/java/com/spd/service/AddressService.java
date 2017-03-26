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
                userOptional.get().getId().equals(announcementOptional.get().getUser().getId())) {
            address = addressRepository.save(address);
            announcementOptional.get().setAddress(address);
            announcementService.saveAnnouncement(announcementOptional.get());
            return address;
        }
        return new Address();
    }

    public void deleteAddress(String email, int idAnnouncement) {
        Optional<User> userOptional = userService.getByEmail(email);
        Optional<Announcement> announcementOptional = announcementService.getById(idAnnouncement);
        if (userOptional.isPresent() && announcementOptional.isPresent() &&
                userOptional.get().getEmail().equals(announcementOptional.get().getUser().getEmail())) {
            addressRepository.delete(announcementOptional.get().getAddress());
        }
    }

    public Address getAddressByAnnouncementId(String email, int idAnnouncement) {
        Optional<Announcement> announcementOptional = announcementService.getById(idAnnouncement);
        Optional<User> userOptional = userService.getByEmail(email);
        if (announcementOptional.isPresent() && userOptional.isPresent()) {
            if (announcementOptional.get().getUser().getEmail().equals(userOptional.get().getEmail())) {
                return announcementOptional.get().getAddress();
            }
        }
        else {
            // TODO
        }
        return null;
    }
}
