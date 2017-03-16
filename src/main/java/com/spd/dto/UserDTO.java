package com.spd.dto;

import java.util.HashSet;


public class UserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private int imageId;

    private boolean active;

    //private Set<UserEmailDTO> userEmails = new HashSet<>();

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

}
