package com.spd.bean;

import java.math.BigDecimal;

public class PriceBean {

    private Integer idAnnouncement;
    private BigDecimal price;
    private String type;

    public Integer getIdAnnouncement() {
        return idAnnouncement;
    }

    public void setIdAnnouncement(Integer idAnnouncement) {
        this.idAnnouncement = idAnnouncement;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
