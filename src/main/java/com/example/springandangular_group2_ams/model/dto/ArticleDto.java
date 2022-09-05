package com.example.springandangular_group2_ams.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private UUID articleId;
    private String title;
    private String description;
    private AppUserDto teacher;




}
