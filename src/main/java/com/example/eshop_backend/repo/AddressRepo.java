package com.example.eshop_backend.repo;

import com.example.eshop_backend.model.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import java.util.UUID;

public interface AddressRepo extends JpaRepository<Address, UUID> {

    @Transactional
    @Modifying
    Address deleteAddressById(UUID uuid);
}
