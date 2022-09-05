package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import com.example.springandangular_group2_ams.model.dto.CommentDto;
import com.example.springandangular_group2_ams.model.request.ArticleRequest;
import com.example.springandangular_group2_ams.model.request.CommentRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ArticleService {

    ArticleDto findById(UUID articleId);

    Page<ArticleDto> fetch(Integer page, Integer size);

    Boolean delete(UUID articleId);

    ArticleDto createPost(ArticleRequest articleRequest);

    ArticleDto updatePost(UUID id, ArticleRequest articleRequest);

    List<CommentDto> fetchCommentByArticleId(UUID articleId);

    CommentDto addComments(UUID articleId, CommentRequest commentRequest);
}
