package com.example.springandangular_group2_ams.repository;

import com.example.springandangular_group2_ams.model.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
