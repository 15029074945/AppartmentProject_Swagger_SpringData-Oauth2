package com.spd.bean;

public class ErrorModelBean {

    private String message;
    private Integer code;

    public ErrorModelBean(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMassege() {
        return message;
    }

    public void setMassege(String massege) {
        this.message = massege;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
