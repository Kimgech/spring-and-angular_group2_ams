package com.example.springandangular_group2_ams.controller;

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
            res.setMessage("teacher found");
            res.setStatus("200");
            res.setPayload(payload);
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
            var payload = appUserService.createUser(appUserRequest);
            res.setMessage("created user");
            res.setStatus("201");
            res.setPayload(payload);
        } catch (Exception e) {
            res.setMessage(e.getMessage());
            res.setStatus("500");
        }
        return res;

    }

    @DeleteMapping("/{id}")
    public SuccessResponse<?> delete(@PathVariable UUID id) {
        var res = new SuccessResponse<>();
        try{
            var payload = appUserService.deleteUser(id);
                res.setMessage("deleted article with id: " + id);
                res.setStatus("200");
                res.setPayload(payload);
        }catch(Exception e){
                res.setMessage(e.getMessage());
                res.setStatus("500");
        }
        return res;
    }

    @PutMapping("/{id}")
    public SuccessResponse<?> update(@RequestBody AppUserRequest appUserRequest, @PathVariable UUID id) {
        var res = new SuccessResponse<>();
        try {
            var payload = appUserService.updateUser(id, appUserRequest);
            res.setMessage("");
            res.setStatus("201");
            res.setPayload(payload);
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

        var res = new PageResponse<>();
        try{
            // check size and page
            if(size > 0 || page > 0){
                var payload = appUserService.fetchUser(page - 1, size);
                res.setMessage("successfully fetched teachers");
                res.setStatus("200");
                res.setPayload(payload.getContent());
                // check size
                if(page <= payload.getTotalPages()){
                    if (page == payload.getTotalPages()) {
                        res.setSize(((int) payload.getTotalElements() - (size * (page - 1))));
                    }
                    res.setSize(payload.getSize());
                }else{
                    res.setSize(0);
                }
                res.setPage(page);
                res.setTotalPages(payload.getTotalPages());
                res.setTotalElements(payload.getTotalElements());
            }else {
                res.setMessage("java.lang.IllegalStateException: page cannot be smaller than 1");
                res.setStatus("500");
            }
        }catch (Exception e){
            res.setMessage(e.getMessage());
            res.setStatus("500");
        }

        return res;
    }
}
