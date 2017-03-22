package com.spd.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "ANNOUNCEMENT")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "HIDDEN")
    private Boolean hidden;

    @Column(name = "ROOM")
    private Integer room;

    @Column(name = "LIVING_PLACES")
    private Integer livingPlaces;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "ACTIVE")
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @OneToMany(mappedBy = "announcement", fetch = FetchType.LAZY)
    private List<AnnouncementFacility> announcementFacilities;

    @OneToMany(mappedBy = "announcement", fetch = FetchType.LAZY)
    private List<Price> prices;

    @OneToMany(mappedBy = "announcement", fetch = FetchType.LAZY)
    private List<AnnouncementImage> announcementImages;

    @OneToMany(mappedBy = "announcement", fetch = FetchType.LAZY)
    private List<BookingRequest> bookingRequests;

    @OneToMany(mappedBy = "announcement", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "announcement", fetch = FetchType.LAZY)
    private List<Conversation> conversations;

    @OneToMany(mappedBy = "announcement", fetch = FetchType.LAZY)
    private List<Favorite> favorites;

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

    public List<AnnouncementFacility> getAnnouncementFacilities() {
        return announcementFacilities;
    }

    public void setAnnouncementFacilities(List<AnnouncementFacility> announcementFacilities) {
        this.announcementFacilities = announcementFacilities;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public List<AnnouncementImage> getAnnouncementImages() {
        return announcementImages;
    }

    public void setAnnouncementImages(List<AnnouncementImage> announcementImages) {
        this.announcementImages = announcementImages;
    }

    public List<BookingRequest> getBookingRequests() {
        return bookingRequests;
    }

    public void setBookingRequests(List<BookingRequest> bookingRequests) {
        this.bookingRequests = bookingRequests;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }
}
