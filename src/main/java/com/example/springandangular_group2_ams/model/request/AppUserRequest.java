package com.example.springandangular_group2_ams.model.request;

import com.example.springandangular_group2_ams.model.entities.AppUser;
import com.example.springandangular_group2_ams.model.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRequest {
    private String name;
    private Role role;

    public AppUser toEntity(){
        return new AppUser(null, this.name, this.role);
    }

    public AppUser toEntities(UUID id) {
        return new AppUser ( id,this.name, this.role);
    }
}

