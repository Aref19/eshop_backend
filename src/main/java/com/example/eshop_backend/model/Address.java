package com.example.eshop_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
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


    @ManyToOne()
    @JoinColumn(name = "Provider_Id")
    private Provider provider;


}
