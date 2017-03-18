package com.spd.dto;


public class UserRegistrationDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean termsChecked;

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

    public boolean isTermsChecked() {
        return termsChecked;
    }

    public void setTermsChecked(boolean termsChecked) {
        this.termsChecked = termsChecked;
    }
}
