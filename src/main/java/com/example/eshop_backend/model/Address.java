package com.example.eshop_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.keycloak.jose.jwk.JWK;

import java.io.Serializable;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    @NonNull
    @Id
    @JsonIgnore
    private UUID id;
    @NonNull
    private String plz;
    @NonNull
    private String str;
    @NonNull
    private String number;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    private User user;
}
