package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import com.example.springandangular_group2_ams.model.entities.Article;
import com.example.springandangular_group2_ams.repository.ArticleRepository;
import com.example.springandangular_group2_ams.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ArticleServiceImp implements ArticleService {
    private final ArticleRepository articleRepository;

    @Override
    public ArticleDto findById(UUID articleId) {
        var article = articleRepository.findById(articleId);
        if (article.isPresent()) {
            return article.get().toDto();
        }
        throw new NoSuchElementException("article not found");
    }

    @Override
    public Page<ArticleDto> fetch(Integer page, Integer size) {
        var pageRequest = PageRequest.of(page,size);

        var result = articleRepository.findAll(pageRequest);

        return result.map(Article::toDto);
    }

    @Override
    public Boolean delete(UUID articleId) {
        //find article
        var cat =  articleRepository.findById(articleId);
        if(cat.isPresent()){
            articleRepository.delete(cat.get());
            return true;
        }
        throw new NoSuchElementException("article not found");
//        articleRepository.deleteById(articleId);
//        return true;
    }
}








