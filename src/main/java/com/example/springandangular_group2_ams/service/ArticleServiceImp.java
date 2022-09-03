package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import com.example.springandangular_group2_ams.model.request.ArticleRequest;
import com.example.springandangular_group2_ams.repository.AppUserRepository;
import com.example.springandangular_group2_ams.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ArticleServiceImp implements ArticleService{
    private final ArticleRepository articleRepository;
    private final AppUserRepository appUserRepository;
    @Override
    public ArticleDto createPost(ArticleRequest articleRequest) {
        var article = articleRequest.toEntity();
        var user = appUserRepository.findById(articleRequest.getUserId());

        article.setUser(user.get());
        return articleRepository.save(article).toDto();
    }

    @Override
    public ArticleDto updatePost(UUID id, ArticleRequest articleRequest) {
        return null;
    }
}
