package com.spd.bean;

import java.util.List;

public class FavoriteBean {

    private Integer idFavorite;
    private Integer idAnnouncement;
    private AnnouncementBean announcementBean;
    private AddressBean addressBean;
    private List<FacilityBean> facilityBeans;
    private List<PriceBean> priceBeans;

    public Integer getIdFavorite() {
        return idFavorite;
    }

    public void setIdFavorite(Integer idFavorite) {
        this.idFavorite = idFavorite;
    }

    public Integer getIdAnnouncement() {
        return idAnnouncement;
    }

    public void setIdAnnouncement(Integer idAnnouncement) {
        this.idAnnouncement = idAnnouncement;
    }

    public AnnouncementBean getAnnouncementBean() {
        return announcementBean;
    }

    public void setAnnouncementBean(AnnouncementBean announcementBean) {
        this.announcementBean = announcementBean;
    }

    public AddressBean getAddressBean() {
        return addressBean;
    }

    public void setAddressBean(AddressBean addressBean) {
        this.addressBean = addressBean;
    }

    public List<FacilityBean> getFacilityBeans() {
        return facilityBeans;
    }

    public void setFacilityBeans(List<FacilityBean> facilityBeans) {
        this.facilityBeans = facilityBeans;
    }

    public List<PriceBean> getPriceBeans() {
        return priceBeans;
    }

    public void setPriceBeans(List<PriceBean> priceBeans) {
        this.priceBeans = priceBeans;
    }

}
