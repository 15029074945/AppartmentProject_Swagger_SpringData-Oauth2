package com.spd.dto;

import java.util.ArrayList;
import java.util.List;

public class UserInformationDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> extraTelephones = new ArrayList<>();
    private List<String> extraEmails = new ArrayList<>();

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

    public List<String> getExtraTelephones() {
        return extraTelephones;
    }

    public void setExtraTelephones(List<String> extraTelephones) {
        this.extraTelephones = extraTelephones;
    }

    public List<String> getExtraEmails() {
        return extraEmails;
    }

    public void setExtraEmails(List<String> extraEmails) {
        this.extraEmails = extraEmails;
    }

    @Override
    public String toString() {
        return "UserInformationDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", extraTelephones=" + extraTelephones +
                ", extraEmails=" + extraEmails +
                '}';
    }
}
