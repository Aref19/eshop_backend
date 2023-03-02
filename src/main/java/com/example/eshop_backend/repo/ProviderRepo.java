package com.example.eshop_backend.repo;

import com.example.eshop_backend.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface ProviderRepo extends JpaRepository<Provider, UUID> {

    public Optional<Provider> findByName(String name);

    public  Optional<Provider> findByEmail(String e);
}
