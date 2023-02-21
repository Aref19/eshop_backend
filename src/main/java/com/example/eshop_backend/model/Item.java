package com.example.eshop_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item   {
    @NonNull
    @Id
    @JsonIgnore
    private UUID id ;
    @NonNull
    private String price;
    @NonNull
    private String title;
    @NonNull
    private String des;


    @ManyToOne()
    @JoinColumn(name = "Provider_Id")
    private Provider provider;
    @OneToMany(mappedBy = "item",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private Set<Image> image;

}
