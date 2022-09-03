package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import com.example.springandangular_group2_ams.model.request.ArticleRequest;

import java.util.UUID;

public interface ArticleService {
    ArticleDto createPost(ArticleRequest articleRequest);

    ArticleDto updatePost(UUID id, ArticleRequest articleRequest);
}
