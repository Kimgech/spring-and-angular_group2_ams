package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ArticleService {

    ArticleDto findById(UUID articleId);

    Page<ArticleDto> fetch(Integer page, Integer size);


}
