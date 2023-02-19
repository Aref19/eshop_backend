package com.example.eshop_backend.repo;

import com.example.eshop_backend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepo extends JpaRepository<Image, UUID> {
}
