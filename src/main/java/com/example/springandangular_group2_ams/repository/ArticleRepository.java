package com.example.springandangular_group2_ams.repository;

import com.example.springandangular_group2_ams.model.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArticleRepository extends JpaRepository<Article, UUID> {

//    Page<Article> findAllByIsPublished(Pageable pageable);

//    Page<Article> getArticlesByIsPublished(Pageable pageable);


}
