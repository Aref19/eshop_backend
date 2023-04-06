package com.example.eshop_backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    @JsonIgnore
    @NonNull
    private UUID id;
    @NonNull
    private String url;

}
