package com.spd.enums;

public enum PriceType {
    PER_DAY("PER DAY"),
    PER_MONTH("PER MONTH");

    private final String type;

    PriceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
