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
    public SuccessResponse<?> fetchById(@PathVariable UUID id) {
        var res = new SuccessResponse<>();
        try {
            var payload = appUserService.fetchById(id);
            if (id.toString().isEmpty()) {
                res.setMessage("Not found");
                res.setStatus("206");
            } else {
                res.setMessage("teacher found");
                res.setStatus("200");
                res.setPayload(payload);
            }
        } catch (Exception e) {
            res.setMessage(e.getMessage());
            res.setStatus("206");
        }

        return res;
    }

    @PostMapping
    public SuccessResponse<?> create(@RequestBody AppUserRequest appUserRequest) {
        var res = new SuccessResponse<>();
        try {
            var payload = appUserService.create(appUserRequest);
            if (appUserRequest.getName().isEmpty()) {
                res.setMessage("Not found");
                res.setStatus("500");
            } else {
                res.setMessage("");
                res.setStatus("201");
                res.setPayload(payload);
            }
        } catch (Exception e) {
            res.setMessage(e.getMessage());
            res.setStatus("500");
        }
        return res;

    }

    @DeleteMapping("/{id}")
    public SuccessResponse<?> delete(@PathVariable UUID id) {
        var payload = appUserService.delete(id);
        var res = new SuccessResponse<>();
        res.setMessage("delete appUser successfully");
        res.setStatus("200");
        res.setPayload(payload);
        return res;
    }

    @PutMapping("/{id}")
    public SuccessResponse<?> update(@RequestBody AppUserRequest appUserRequest, @PathVariable UUID id) {
        var res = new SuccessResponse<>();
        try {
            var payload = appUserService.update(id, appUserRequest);
            if (id.toString().isEmpty()) {
                res.setMessage("");
                res.setStatus("500");
            } else {
                res.setMessage("");
                res.setStatus("201");
                res.setPayload(payload);
            }
        } catch (Exception e) {
            res.setMessage(e.getMessage());
            res.setStatus("500");
        }
        return res;
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
        if(page <= payload.getTotalPages()){
            if (page == payload.getTotalPages()){
                res.setSize(((int) payload.getTotalElements()-(size*(page -1))));
            }else {
                res.setSize(payload.getSize());
            }
        }else{
            res.setSize(0);
        }
        res.setPage(page);
        res.setTotalPages(payload.getTotalPages());
        res.setTotalElements(payload.getTotalElements());
        return res;
    }
}
