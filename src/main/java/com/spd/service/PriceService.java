package com.spd.service;

import com.spd.bean.PriceBean;
import com.spd.entity.Announcement;
import com.spd.entity.Price;
import com.spd.entity.PriceType;
import com.spd.entity.User;
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
    private final PriceTypeService priceTypeService;

    @Autowired
    public PriceService(UserService userService, AnnouncementService announcementService, PriceTypeService priceTypeService, PriceRepository priceRepository, PriceTypeService priceTypeService1) {
        this.userService = userService;
        this.announcementService = announcementService;
        this.priceRepository = priceRepository;
        this.priceTypeService = priceTypeService1;
    }

    public List<PriceBean> getPrices(String email, int idAnnouncement) {
        Optional<User> userOptional = userService.getByEmail(email);
        Optional<Announcement> announcementOptional = announcementService.getById(idAnnouncement);
        if (userOptional.isPresent() && announcementOptional.isPresent() &&
                userOptional.get().getEmail().equals(announcementOptional.get().getUser().getEmail())) {
            List<PriceBean> result = new ArrayList<>();
            List<Price> prices = priceRepository.findByAnnouncementId(idAnnouncement);
            for (Price price : prices) {
                PriceBean priceBean = new PriceBean();
                priceBean.setIdAnnouncement(idAnnouncement);
                priceBean.setPrice(price.getPrice());
                priceBean.setType(price.getPriceType().getType());
                result.add(priceBean);
            }
            return result;
        }
        else {
            return new ArrayList<>();
        }
    }

    public void savePrices(String email, List<PriceBean> priceBeans) {
        if (priceBeans.size() == 0) {
            return;
        }
        int idAnnouncement = priceBeans.get(0).getIdAnnouncement();
        Optional<User> userOptional = userService.getByEmail(email);
        Optional<Announcement> announcementOptional = announcementService.getById(idAnnouncement);
        if (userOptional.isPresent() && announcementOptional.isPresent() &&
                userOptional.get().getEmail().equals(announcementOptional.get().getUser().getEmail())) {
            for (PriceBean priceBean : priceBeans) {
                Price price = new Price();
                price.setAnnouncement(announcementOptional.get());
                PriceType priceType = priceTypeService.getPriceTypeByType(priceBean.getType());
                price.setPriceType(priceType);
                price.setPrice(priceBean.getPrice());
                priceRepository.save(price);
            }
        }
    }

    public void updatePrices(String email, List<PriceBean> priceBeans) {
        savePrices(email, priceBeans);
    }

    public void deletePrices(String email, int idAnnouncement) {
        Optional<User> userOptional = userService.getByEmail(email);
        Optional<Announcement> announcementOptional = announcementService.getById(idAnnouncement);
        if (userOptional.isPresent() && announcementOptional.isPresent() &&
                userOptional.get().getEmail().equals(announcementOptional.get().getUser().getEmail())) {
            List<Price> prices = priceRepository.findByAnnouncementId(idAnnouncement);
            priceRepository.delete(prices);
        }
    }
}
