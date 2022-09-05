package com.example.springandangular_group2_ams.repository;

import com.example.springandangular_group2_ams.model.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {












}
