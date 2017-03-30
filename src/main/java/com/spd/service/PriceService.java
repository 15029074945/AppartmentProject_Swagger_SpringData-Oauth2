package com.spd.service;

import com.spd.bean.PriceBean;
import com.spd.entity.Announcement;
import com.spd.entity.Price;
import com.spd.entity.User;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        User user = userService.getByEmail(email);
        Announcement announcement = announcementService.getByIdAndUserId(idAnnouncement, user.getId());

        List<Price> prices = priceRepository.findByAnnouncementIdAndActiveTrue(announcement.getId());

        return objectMapper.mapAsList(prices, PriceBean.class);
    }

    public void savePrices(String email, PriceBean priceBeans, int idAnnouncement) {
        User user = userService.getByEmail(email);
        Announcement announcement = announcementService.getByIdAndUserId(idAnnouncement, user.getId());

        List<Price> prices = priceRepository.findByAnnouncementIdAndActiveTrue(announcement.getId());

        for (Price price : prices) {
            if (price.getType().equals(priceBeans.getType())) {
                price.setPrice(priceBeans.getPrice());
                priceRepository.save(price);
                return;
            }
        }

        Price price = objectMapper.map(priceBeans, Price.class);
        price.setActive(true);
        price.setAnnouncement(announcement);
        priceRepository.save(price);
    }

    public void updatePrices(String email, PriceBean priceBeans, int idAnnouncement) {
        savePrices(email, priceBeans, idAnnouncement);
    }

    public void deletePrices(String email, int idAnnouncement) {
        User user = userService.getByEmail(email);
        Announcement announcement = announcementService.getByIdAndUserId(idAnnouncement, user.getId());
        List<Price> prices = priceRepository.findByAnnouncementIdAndActiveTrue(announcement.getId());
        prices.forEach(price -> {
            price.setActive(false);
            priceRepository.save(price);
        });
    }
}
