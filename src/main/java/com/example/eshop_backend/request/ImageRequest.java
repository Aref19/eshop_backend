package com.example.eshop_backend.request;

import com.example.eshop_backend.model.Image;
import com.example.eshop_backend.model.Item;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
public class ImageRequest {


    private MultipartFile url;

    public static Set<Image> ImageRequestToImage(Set<MultipartFile> image) {
        return image.stream().map(imgeRe ->
                new Image(
                        UUID.randomUUID(),
                        imgeRe.getName()
                )
        ).collect(Collectors.toSet());
    }
}
