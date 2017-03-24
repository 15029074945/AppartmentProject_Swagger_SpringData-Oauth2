package com.spd.bean;

import java.math.BigDecimal;

public class AddressBean {

    private Integer idAnnouncement;
    private String country;
    private String city;
    private String region;
    private String street;
    private String Appartment;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public Integer getIdAnnouncement() {
        return idAnnouncement;
    }

    public void setIdAnnouncement(Integer idAnnouncement) {
        this.idAnnouncement = idAnnouncement;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAppartment() {
        return Appartment;
    }

    public void setAppartment(String appartment) {
        Appartment = appartment;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
