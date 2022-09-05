package com.example.springandangular_group2_ams.repository;

import com.example.springandangular_group2_ams.model.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface BookmarkRepository extends JpaRepository<Article,UUID> {



    @Query(value ="select a.* from articles a  inner join app_users u on a.teacher_id = u.id " +
            "inner join user_bookmarked_articles b on b.app_user_id = a.teacher_id " +
            " where b.app_user_id= :id", nativeQuery = true)
    Page<Article> findAllById(UUID id,Pageable pageable);


//    inner join app_users u on a.teacher_id = u.id
    @Query(value ="select distinct a.* from articles a  " +
            "inner join user_bookmarked_articles b on b.bookmarked_article_id = a.id " +
//            "inner join app_users u on  u.id = user_bookmarked_articles.app_user_id  " +
            " where b.app_user_id= :teacherId and b.bookmarked_article_id= :articleId ", nativeQuery = true)
    Article findById(UUID teacherId,UUID  articleId);


}
