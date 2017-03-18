package com.spd.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "IMAGE_ID")
    private int imageId;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "STATUS")
    private boolean status;

    @Column(name = "VERIFICATION_KEY")
    private String verificationKey;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserEmail> userEmails = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserTelephone> userTelephones = new ArrayList<>();

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getVerificationKey() {
        return verificationKey;
    }

    public void setVerificationKey(String verificationKey) {
        this.verificationKey = verificationKey;
    }

    public List<UserEmail> getUserEmails() {
        return userEmails;
    }

    public void setUserEmails(List<UserEmail> userEmails) {
        this.userEmails = userEmails;
    }

    public List<UserTelephone> getUserTelephones() {
        return userTelephones;
    }

    public void setUserTelephones(List<UserTelephone> userTelephones) {
        this.userTelephones = userTelephones;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", imageId=" + imageId +
                ", createdDate=" + createdDate +
                ", updateDate=" + updateDate +
                ", active=" + active +
                ", status=" + status +
                ", verificationKey='" + verificationKey + '\'' +
                ", userEmails=" + userEmails +
                ", userTelephones=" + userTelephones +
                '}';
    }
}
