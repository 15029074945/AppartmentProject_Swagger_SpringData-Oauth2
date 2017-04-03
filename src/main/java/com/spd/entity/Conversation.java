package com.spd.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CONVERSATION")
public class Conversation {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "ACTIVE")
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANNOUNCEMENT_ID")
    private Announcement announcement;

    @OneToMany(mappedBy = "conversation", fetch = FetchType.LAZY)
    private List<Attender> attenders;

    @OneToMany(mappedBy = "conversation", fetch = FetchType.LAZY)
    private List<Message> messages;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public List<Attender> getAttenders() {
        return attenders;
    }

    public void setAttenders(List<Attender> attenders) {
        this.attenders = attenders;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }



}
