package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.AppUserDto;
import com.example.springandangular_group2_ams.model.entities.AppUser;
import com.example.springandangular_group2_ams.model.request.AppUserRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AppUserService {
    
    AppUserDto fetchById(UUID id);

    Page<AppUserDto> fetchUser(Integer page, Integer size);

    AppUserDto createUser(AppUserRequest appUserRequest);

    Boolean deleteUser(UUID appUserId);

    AppUserDto updateUser(UUID appUserId, AppUserRequest appUserRequest);
}
