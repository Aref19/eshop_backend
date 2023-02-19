package com.example.eshop_backend.repo;

import com.example.eshop_backend.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProviderRepo extends JpaRepository<Provider, UUID> {
}
