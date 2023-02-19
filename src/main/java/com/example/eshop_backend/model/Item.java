package com.example.eshop_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    private UUID id ;
    private String price;
    private String title;
    private String des;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "Provider_Id")
    private Provider provider;


    @OneToMany(mappedBy = "item")
    private Set<Image> image;

}
