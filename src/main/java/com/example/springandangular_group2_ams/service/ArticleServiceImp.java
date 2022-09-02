package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import com.example.springandangular_group2_ams.repository.ArticleRepository;
import com.example.springandangular_group2_ams.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ArticleServiceImp implements ArticleService {
    private final ArticleRepository articleRepository;
//    private final CategoryRepository categoryRepository;

    @Override
    public ArticleDto findById(UUID articleId) {
        var article = articleRepository.findById(articleId);
        if (article.isPresent()) {
            return article.get().toDto();
        }
        throw new NoSuchElementException("Article not found");
    }

    @Override
    public Page<ArticleDto> fetch(Integer page, Integer size) {
        return null;
    }
}
