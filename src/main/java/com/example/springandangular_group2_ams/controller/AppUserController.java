package com.example.springandangular_group2_ams.controller;

import com.example.springandangular_group2_ams.model.dto.AppUserDto;
import com.example.springandangular_group2_ams.model.request.AppUserRequest;
import com.example.springandangular_group2_ams.model.response.PageResponse;
import com.example.springandangular_group2_ams.model.response.SuccessResponse;
import com.example.springandangular_group2_ams.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;
    @GetMapping("{id}")
    public SuccessResponse<?>fetchById(@PathVariable UUID id){
        var payload =  appUserService.fetchById(id);
        var res = new SuccessResponse<>();
        res.setMessage("teacher found");
        res.setStatus("200");
        res.setPayload(payload);
        return res;
    }

    @PostMapping
    public SuccessResponse<?>create(@RequestBody AppUserRequest categoryRequest) {
        var payload =  appUserService.create(categoryRequest);
        var res = new SuccessResponse<>();
        res.setMessage("");
        res.setStatus("201");
        res.setPayload(payload);
        return res;
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<?>delete(@PathVariable UUID id){
     var payload =  appUserService.delete(id);
        var res = new SuccessResponse<>();
               res.setMessage("delete appUser successfully");
               res.setStatus("200");
               res.setPayload(payload);
                return res;
    }

    @PutMapping("/{id}")
    public AppUserDto update(@RequestBody AppUserRequest appUserRequest, @PathVariable UUID id){
        return appUserService.update(id, appUserRequest);
    }

    @GetMapping
    public PageResponse<?> fetchAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        var payload = appUserService.fetch(page - 1, size);

        var res = new PageResponse<>();
        res.setMessage("successfully fetched teachers");
        res.setStatus("200");
        res.setPayload(payload.getContent());
        res.setPage(payload.getNumber() + 1);
        res.setSize(payload.getSize());
        res.setTotalPages(payload.getTotalPages());
        res.setTotalElements(payload.getTotalElements());
        return res;
    }
}
