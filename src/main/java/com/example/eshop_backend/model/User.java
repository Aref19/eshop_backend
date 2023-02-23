package com.example.eshop_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    private String userName;
    private String emailId;
    private String password;
    @JsonIgnore
    private String role;

    public User(String userName, String emailId, String password) {
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;

    }

    public String getUserName() {
        return userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
