package com.spd.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PRICE_TYPE")
public class PriceType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "TYPE")
    private String type;

    @OneToMany(mappedBy = "priceType", fetch = FetchType.LAZY)
    private List<Price> prices;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}
