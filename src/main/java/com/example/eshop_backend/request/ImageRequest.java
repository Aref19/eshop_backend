package com.example.eshop_backend.request;

import com.example.eshop_backend.model.Image;
import com.example.eshop_backend.model.Item;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Getter
@Setter
public class ImageRequest {


    private String url;

    public  static List<Image> ImageRequestToImage(List<ItemRequest> imageRequests, List<Item> items){
        List<Image> images=new ArrayList<>();
        for (int i=0;i<items.size();i++) {
            List<ImageRequest> imageRequest=imageRequests.get(i).getImage().stream().toList();
            for (int y=0;y<imageRequest.toArray().length;y++)
            images.add(new Image(UUID.randomUUID(), imageRequest.get(y).url, items.get(i)));
        }
        return images;
    }
}
