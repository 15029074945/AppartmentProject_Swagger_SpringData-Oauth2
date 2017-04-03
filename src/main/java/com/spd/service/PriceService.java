package com.spd.service;

import com.spd.bean.PriceBean;
import com.spd.entity.Announcement;
import com.spd.entity.Price;
import com.spd.entity.User;
import com.spd.exception.AlreadyHavePrice;
import com.spd.exception.NoSuchPriceException;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    private final UserService userService;
    private final AnnouncementService announcementService;
    private final PriceRepository priceRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PriceService(UserService userService, AnnouncementService announcementService, PriceRepository priceRepository, ObjectMapper objectMapper) {
        this.userService = userService;
        this.announcementService = announcementService;
        this.priceRepository = priceRepository;
        this.objectMapper = objectMapper;
    }

    public List<PriceBean> getPrices(String email, int idAnnouncement) {
        List<Price> prices = getUserPrices(email, idAnnouncement);
        return objectMapper.mapAsList(prices, PriceBean.class);
    }

    public List<PriceBean> getPricesBeans(Announcement announcement) {
        List<Price> prices = getUserPrices(announcement);
        return objectMapper.mapAsList(prices, PriceBean.class);
    }

    private List<Price> getUserPrices(Announcement announcement) {
        return priceRepository.findByAnnouncementIdAndActiveTrue(announcement.getId());
    }

    public void savePrices(String email, PriceBean priceBeans, int idAnnouncement) {
        User user = userService.getByEmail(email);
        Announcement announcement = announcementService.getByIdAndUserId(idAnnouncement, user.getId());

        Optional<Price> priceOptional = priceRepository.findOneByTypeAndAnnouncementIdAndActiveTrue(priceBeans.getType(), announcement.getId());
        if (priceOptional.isPresent()) {
            throw new AlreadyHavePrice("Already have price with type " + priceBeans.getType());
        }
        else {
            Price price = objectMapper.map(priceBeans, Price.class);
            price.setActive(true);
            price.setAnnouncement(announcement);
            priceRepository.save(price);
        }
    }

    public void updatePrices(String email, PriceBean priceBeans, int idAnnouncement) {
        User user = userService.getByEmail(email);
        Announcement announcement = announcementService.getByIdAndUserId(idAnnouncement, user.getId());

        Optional<Price> priceOptional = priceRepository.findOneByTypeAndAnnouncementIdAndActiveTrue(priceBeans.getType(), announcement.getId());
        priceOptional.map(price -> {
            price.setPrice(priceBeans.getPrice());
            priceRepository.save(price);
            return price;
        })
        .orElseThrow(() -> new NoSuchPriceException("No such price " + priceBeans.getType()));
    }

    public void deletePrices(String email, int idAnnouncement) {
        List<Price> prices = getUserPrices(email, idAnnouncement);
        prices.forEach(price -> {
            price.setActive(false);
            priceRepository.save(price);
        });
    }

    public void deletePrice(String email, int idAnnouncement, String type) {
        List<Price> prices = getUserPrices(email, idAnnouncement);
        prices.forEach(price -> {
            if (price.getType().equals(type)) {
                price.setActive(false);
                priceRepository.save(price);
            }
        });
    }

    private List<Price> getUserPrices(String email, int idAnnouncement) {
        User user = userService.getByEmail(email);
        Announcement announcement = announcementService.getByIdAndUserId(idAnnouncement, user.getId());
        return priceRepository.findByAnnouncementIdAndActiveTrue(announcement.getId());
    }
}
