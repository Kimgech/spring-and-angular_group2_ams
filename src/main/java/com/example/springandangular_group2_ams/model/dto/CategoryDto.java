package com.example.springandangular_group2_ams.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
}
