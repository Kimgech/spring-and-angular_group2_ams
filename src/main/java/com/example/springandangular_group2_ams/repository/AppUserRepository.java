package com.example.springandangular_group2_ams.repository;

import com.example.springandangular_group2_ams.model.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
}
