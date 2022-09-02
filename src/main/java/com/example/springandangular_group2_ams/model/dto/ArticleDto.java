package com.example.springandangular_group2_ams.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ArticleDto implements Serializable {

    private UUID id;
    private String title;
    private String description;
    private Boolean isPublished;
    private List<CategoryDto> articleCategory;

    public ArticleDto(UUID id, String title, String description, Boolean isPublished, List<CategoryDto> categories){
        this.id = id;
        this.title = title;
        this.description=description;
        this.isPublished= isPublished;
        this.articleCategory=categories;
    }

}
