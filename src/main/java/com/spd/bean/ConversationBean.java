package com.spd.bean;

import java.util.List;

public class ConversationBean {
    private Integer id;
    private List<Integer> attenders;
    private Integer announcementId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public List<Integer> getAttenders() {
        return attenders;
    }

    public void setAttenders(List<Integer> attenders) {
        this.attenders = attenders;
    }

    public Integer getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Integer announcementId) {
        this.announcementId = announcementId;
    }
}
