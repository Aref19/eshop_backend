package com.example.eshop_backend.services;


import com.amazonaws.services.dynamodbv2.xspec.S;
import com.example.eshop_backend.Until;
import com.example.eshop_backend.config.BuketName;
import com.example.eshop_backend.exception.NotFoundException;
import com.example.eshop_backend.model.Item;
import com.example.eshop_backend.model.Provider;
import com.example.eshop_backend.repo.AddressRepo;
import com.example.eshop_backend.repo.ImageRepo;
import com.example.eshop_backend.repo.ItemRepo;
import com.example.eshop_backend.repo.ProviderRepo;
import com.example.eshop_backend.request.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProviderService {

    @Autowired
    ItemRepo itemRepo;
    @Autowired
    ProviderRepo providerRepo;
    @Autowired
    ImageRepo imageRepo;
    @Autowired
    AddressRepo addressRepo;
    @Autowired
    AmazonService amazonService;

    public void addItems(ItemRequest itemRequestList) {
        String email = Until.emailProvider();
        Optional<Provider> provider = providerRepo.findByEmail(email);
        if (provider.isEmpty()) {
            throw new NotFoundException("No Provider Found");
        }
        Item items = ItemRequest.ItemRequestToItem(itemRequestList);
        saveImagesInBucket(items, itemRequestList);
        provider.get().setItemSet(new HashSet<>(List.of(items)));
        providerRepo.save(provider.get());
    }

    private void saveImagesInBucket(Item items, ItemRequest itemRequestList) {
          items.getImage().forEach(image ->{
              itemRequestList.getImage().forEach(imageRequest -> {
                  Map<String, String> metadata = new HashMap<>();
                  metadata.put("Content-Type", imageRequest.getContentType());
                  metadata.put("Content-Length", String.valueOf(imageRequest.getSize()));
                  String path = String.format("%s/%s", "eshop-storge", image.getId());
                  String fileName = String.format("%s", imageRequest.getOriginalFilename());
                  amazonService.upload(path, fileName, Optional.of(metadata), imageRequest);
              });
          });

    }

    public Page<Item> getItems(PageReq pageReq) {
        String email = Until.emailProvider();
        Optional<Provider> provider = providerRepo.findByEmail(email);
        if (provider.isPresent()) {
            return Until.setToPage(provider.get().getItemSet(), Until.createPageable(pageReq));
        } else
            throw new NotFoundException("No Provider Found");
    }

    public void updateItem(UpdateItem updateItem) {
        Optional<Item> item = itemRepo.findById(updateItem.getId());
        Item item1 = UpdateItem.updateItemToItem(updateItem);
        if (item.isPresent()) {
            if (!updateItem.getImageSet().isEmpty()) {
                item1.getImage().addAll(item.get().getImage());
            }
            itemRepo.save(item1);
        } else
            throw new NotFoundException("No Item Found");
    }

    public void deleteItem(UUID uuid) {
        itemRepo.deleteById(uuid);
    }

    public void updateProvider(UUID id, ProviderRequest providerRequest) {
        Optional<Provider> existProvider = providerRepo.findById(id);
        if (existProvider.isPresent()) {
            Provider providerToSave = ProviderRequest.mergeProviderRequestToProvider(providerRequest, existProvider.get());
            providerRepo.save(providerToSave);
        } else
            throw new NotFoundException("No Provider Found");
    }

    public void deleteAddress(UUID uuid) {
        addressRepo.deleteAddressById(uuid);
    }
}
//ToDo add old items to new
//ToDo optimize  save Image
//TODO save Urll in Image
// ToDo save icon for Provider in Amazon