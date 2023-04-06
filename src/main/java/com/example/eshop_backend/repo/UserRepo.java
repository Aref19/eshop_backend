package com.example.eshop_backend.repo;

import com.example.eshop_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepo  extends JpaRepository<User, UUID> {
}
