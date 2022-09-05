package com.example.springandangular_group2_ams.model.dto;

import com.example.springandangular_group2_ams.model.entities.AppUser;
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
    private List<CategoryDto> category;

    private AppUserDto user;

    private List<CommentDto> comments;

    public ArticleDto(UUID id, String title, String description, Boolean isPublished, List<CategoryDto> categories, AppUserDto user,List<CommentDto> comments){
        this.id = id;
        this.title = title;
        this.description=description;
        this.isPublished= isPublished;
        this.category=categories;
        this.user=user;
        this.comments=comments;
    }

    public ArticleDto(UUID id, String title, String description, Boolean isPublished, List<CategoryDto> categoryList, AppUserDto user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isPublished = isPublished;
        this.category = categoryList;
        this.user = user;
    }
}
