package com.example.springandangular_group2_ams.model.dto;

import com.example.springandangular_group2_ams.model.entities.Article;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AppUserDto {
        private UUID id;
        private String name;
        private String role;
        }
