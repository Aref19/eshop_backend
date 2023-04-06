package com.example.eshop_backend.repo;

import com.example.eshop_backend.model.Item;
import com.example.eshop_backend.model.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemRepo extends JpaRepository<Item, UUID> {

    public Page<Item> findByTitle(Pageable pageable, String name);




}
