package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import com.example.springandangular_group2_ams.model.request.ArticleRequest;
import org.springframework.data.domain.Page;
import java.util.UUID;

public interface ArticleService {

    ArticleDto findArticleById(UUID articleId);

    Page<ArticleDto> fetchAllArticles(Integer page, Integer size);

    Boolean delete(UUID articleId);

    ArticleDto createPost(ArticleRequest articleRequest);

    ArticleDto updatePost(UUID id, ArticleRequest articleRequest);

//    Page<ArticleDto> getArticlesByIsPublished(Integer page, Integer size);

//    Page<ArticleDto>
}
