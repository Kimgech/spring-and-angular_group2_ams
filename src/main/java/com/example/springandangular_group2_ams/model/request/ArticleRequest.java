package com.example.springandangular_group2_ams.model.request;

import com.example.springandangular_group2_ams.model.entities.Article;
import com.example.springandangular_group2_ams.model.entities.Category;
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
    private List<String> categories;
    private UUID userId;

//for create
    public Article toEntity(){
        var article = new Article();
        article.setTitle(this.title);
        article.setDescription(this.description);
        return article;
    }
// for update
    public Article toEntity(UUID id){
        var article = new Article();
        article.setId(id);
        article.setTitle(this.title);
        article.setDescription(this.description);
        return article;
    }
}
