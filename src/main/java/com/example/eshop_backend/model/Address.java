package com.example.eshop_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Address {
    @NonNull
    @Id
    private UUID id ;
    @NonNull
    private String plz;
    @NonNull
    private String str;
    @NonNull
    private String number;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "Provider_Id")
    private Provider provider;


}
