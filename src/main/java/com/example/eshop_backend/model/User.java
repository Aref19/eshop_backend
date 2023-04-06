package com.example.eshop_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Entity
public class User {

    @Id
    private UUID id;
    private String userName;
    private String emailId;
    @Transient
    private String password;
    @JsonIgnore
    @Transient
    private String role;

    @OneToMany
    private Set<Address> addressSet;



    public User(String userName, String emailId, String password) {
        this.id=UUID.randomUUID();
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;

    }

    public void setAddress(Set<Address> addresses){
        this.addressSet.addAll(addresses);
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
