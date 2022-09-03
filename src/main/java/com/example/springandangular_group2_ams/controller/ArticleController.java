package com.example.springandangular_group2_ams.controller;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import com.example.springandangular_group2_ams.model.request.ArticleRequest;
import com.example.springandangular_group2_ams.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ArticleDto createPost(@RequestBody ArticleRequest articleRequest){
        return articleService.createPost(articleRequest);
    }

    @PutMapping("/{id}")
    public ArticleDto updatePost(@PathVariable UUID id, @RequestBody ArticleRequest articleRequest){
        return articleService.updatePost(id,articleRequest);
    }

}
