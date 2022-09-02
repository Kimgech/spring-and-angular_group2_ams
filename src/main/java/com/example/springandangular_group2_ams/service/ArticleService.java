package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;

public interface ArticleService {

    ArticleDto findById(Long articleId);
}
