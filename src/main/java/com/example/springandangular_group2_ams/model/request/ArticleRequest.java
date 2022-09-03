package com.example.springandangular_group2_ams.model.request;

import com.example.springandangular_group2_ams.model.entities.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {
    private String title;
    private String description;
    private List<CategoryRequest> categories;
    private UUID userId;

    public Article toEntity(){
        var article = new Article();
        article.setTitle(this.title);
        article.setDescription(this.description);
        article.setCategoryList(this.categories.stream().map(category->category.toEntity()).collect(Collectors.toList()));
        return article;
    }
}
