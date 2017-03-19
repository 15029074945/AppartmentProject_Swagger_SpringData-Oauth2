package com.spd.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "ANNOUNCEMENT")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "HIDDEN")
    private boolean hidden;

    @Column(name = "ROOM")
    private int room;

    @Column(name = "LIVING_PLACES")
    private int livingPlaces;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Column(name = "ACTIVE")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user = new User();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address = new Address();

    @OneToMany(mappedBy = "announcement", fetch = FetchType.LAZY)
    private List<AnnouncementFacility> facilities = new ArrayList<>();

    @OneToMany(mappedBy = "announcement", fetch = FetchType.LAZY)
    private List<Price> prices = new ArrayList<>();

    public Announcement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getLivingPlaces() {
        return livingPlaces;
    }

    public void setLivingPlaces(int livingPlaces) {
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<AnnouncementFacility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<AnnouncementFacility> facilities) {
        this.facilities = facilities;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", hidden=" + hidden +
                ", room=" + room +
                ", livingPlaces=" + livingPlaces +
                ", title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", createdDate=" + createdDate +
                ", updateDate=" + updateDate +
                ", active=" + active +
                ", user=" + user +
                ", address=" + address +
                '}';
    }
}
