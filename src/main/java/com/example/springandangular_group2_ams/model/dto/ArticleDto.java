package com.example.springandangular_group2_ams.model.dto;

import com.example.springandangular_group2_ams.model.entities.AppUser;
import com.example.springandangular_group2_ams.model.entities.Category;
import com.example.springandangular_group2_ams.model.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleDto {
    private UUID id;
    private String title;
    private String description;
    private Boolean isPublished;
    private List<CategoryDto> categoryList;
    private AppUserDto user;
    private List<CommentDto> commentList;

    public ArticleDto(UUID id, String title, String description, Boolean isPublished, List<CategoryDto> categoryList, AppUserDto user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isPublished = isPublished;
        this.categoryList = categoryList;
        this.user = user;
    }
}
