package com.example.eshop_backend.repo;

import com.example.eshop_backend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepo extends JpaRepository<Address, UUID> {
}
