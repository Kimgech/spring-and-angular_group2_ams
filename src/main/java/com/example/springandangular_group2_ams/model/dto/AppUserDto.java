package com.example.springandangular_group2_ams.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {

        private UUID id;
        private String name;
        private String role;


}
