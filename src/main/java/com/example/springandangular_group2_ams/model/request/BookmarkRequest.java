package com.example.springandangular_group2_ams.model.request;

import com.example.springandangular_group2_ams.model.entities.Article;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkRequest implements Serializable {

    private UUID articleId;


    public Article toEntity(){
        var article = new Article();
        article.setId(articleId);
        return article;
    }
}
