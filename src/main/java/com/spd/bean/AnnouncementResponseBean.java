package com.spd.bean;

public class AnnouncementResponseBean {

    private Integer id;
    private Boolean hidden;
    private Integer room;
    private Integer livingPlaces;
    private String title;
    private String shortDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getLivingPlaces() {
        return livingPlaces;
    }

    public void setLivingPlaces(Integer livingPlaces) {
        this.livingPlaces = livingPlaces;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
