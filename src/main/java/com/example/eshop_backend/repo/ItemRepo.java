package com.example.eshop_backend.repo;

import com.example.eshop_backend.model.Item;
import com.example.eshop_backend.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemRepo extends JpaRepository<Item, UUID> {

    public List<Item> findByTitle(String name);
    public List<Item> findByProvider(Provider provider);

}
