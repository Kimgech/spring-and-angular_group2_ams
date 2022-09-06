package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.AppUserDto;
import com.example.springandangular_group2_ams.model.entities.AppUser;
import com.example.springandangular_group2_ams.model.request.AppUserRequest;
import com.example.springandangular_group2_ams.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserServiceImp implements AppUserService {

    private final AppUserRepository appUserRepository;


    @Override
    public AppUserDto fetchById(UUID id) {
        var response = appUserRepository.findById(id);
        if (response.isPresent()) return response.get().toDto();
        throw new NoSuchElementException("not found user id: " + id);
    }

    @Override
    public Page<AppUserDto> fetchUser(Integer page, Integer size) {
        var pageRequest = PageRequest.of(page, size);
        var result = appUserRepository.findAll(pageRequest);
        return result.map(AppUser::toDto);
    }

    @Override
    public AppUserDto createUser(AppUserRequest appUserRequest) {
        var user = appUserRequest.toEntity();

        var savedUser = appUserRepository.save(user);
        return savedUser.toDto();
    }

    @Override
    public Boolean deleteUser(UUID appUserId) {
        var cat = appUserRepository.findById(appUserId);
        if (cat.isPresent()) {
            appUserRepository.delete(cat.get());
            return true;
        }
        throw new NoSuchElementException("not found user id: " + appUserId );
    }

    @Override
    public AppUserDto updateUser(UUID appUserId, AppUserRequest appUserRequest) {
        var user = appUserRepository.findById(appUserId);
        if (user.isPresent()){
            var catEntity = appUserRequest.toEntities(appUserId);
            var SaveUser = appUserRepository.save(catEntity);
            return SaveUser.toDto();
        }
        throw new NoSuchElementException("not found user id: " + appUserId);
    }
}
