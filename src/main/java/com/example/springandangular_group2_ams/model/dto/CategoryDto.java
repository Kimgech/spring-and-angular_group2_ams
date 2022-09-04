package com.example.springandangular_group2_ams.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CategoryDto implements Serializable {
    private Long id;
    private String name;


}
