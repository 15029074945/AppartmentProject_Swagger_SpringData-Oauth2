package com.spd.service;

import com.spd.bean.*;
import com.spd.entity.Address;
import com.spd.entity.Announcement;
import com.spd.entity.Favorite;
import com.spd.entity.User;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final AnnouncementService announcementService;
    private final AnnouncementFacilityService announcementFacilityService;
    private final PriceService priceService;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository, UserService userService, ObjectMapper objectMapper, AnnouncementService announcementService, AnnouncementFacilityService announcementFacilityService, PriceService priceService) {
        this.favoriteRepository = favoriteRepository;
        this.userService = userService;
        this.objectMapper = objectMapper;
        this.announcementService = announcementService;
        this.announcementFacilityService = announcementFacilityService;
        this.priceService = priceService;
    }

    public List<FavoriteBean> getFavorites(String email) {
        User user = userService.getByEmail(email);
        List<Favorite> favorites = favoriteRepository.findByUserId(user.getId());
        List<FavoriteBean> favoriteBeans = new ArrayList<>();
        favorites.forEach(favorite -> {
            FavoriteBean favoriteBean = getFavoriteBean(favorite);
            favoriteBeans.add(favoriteBean);
        });
        return favoriteBeans;
    }

    private FavoriteBean getFavoriteBean(Favorite favorite) {
        FavoriteBean favoriteBean = new FavoriteBean();

        favoriteBean.setIdFavorite(favorite.getId());

        Announcement announcement = favorite.getAnnouncement();
        favoriteBean.setIdAnnouncement(announcement.getId());

        AnnouncementBean announcementBean = objectMapper.map(announcement, AnnouncementBean.class);
        favoriteBean.setAnnouncementBean(announcementBean);

        Address address = announcement.getAddress();
        AddressBean addressBean = objectMapper.map(address, AddressBean.class);
        favoriteBean.setAddressBean(addressBean);

        List<FacilityBean> facilityBeans = announcementFacilityService.getFacilityBeans(announcement);
        favoriteBean.setFacilityBeans(facilityBeans);

        List<PriceBean> priceBeans = priceService.getPricesBeans(announcement);
        favoriteBean.setPriceBeans(priceBeans);
        return favoriteBean;
    }

    public FavoriteBean createFavorite(String email, Integer idAnnouncement) {
        User user = userService.getByEmail(email);
        Announcement announcement = announcementService.getById(idAnnouncement);
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setAnnouncement(announcement);
        Favorite newFavorite = favoriteRepository.save(favorite);
        return getFavoriteBean(newFavorite);
    }

    public void deleteFavorite(Integer id) {
        favoriteRepository.delete(id);
    }
}
