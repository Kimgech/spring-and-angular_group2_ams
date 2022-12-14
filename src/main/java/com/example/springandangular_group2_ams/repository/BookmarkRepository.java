package com.example.springandangular_group2_ams.repository;

import com.example.springandangular_group2_ams.model.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.UUID;

public interface BookmarkRepository extends JpaRepository<Article,UUID> {

    //get article by teacherId
    @Query(value ="select a.* from articles a  inner join app_users u on a.teacher_id = u.id " +
            "inner join user_bookmarked_articles b on b.app_user_id = a.teacher_id " +
            " where b.app_user_id= :id", nativeQuery = true)
    Page<Article> findAllById(UUID id,Pageable pageable);


    //get article by teacherId & articleId
    @Query(value ="select distinct a.* from articles a  " +
            "inner join user_bookmarked_articles b on b.bookmarked_article_id = a.id " +
            " where b.app_user_id= :teacherId and b.bookmarked_article_id= :articleId ", nativeQuery = true)
    Article findById(UUID teacherId,UUID  articleId);


    @Modifying
    @Query(value ="delete from user_bookmarked_articles a  " +
            " where a.app_user_id= :teacherId and a.bookmarked_article_id= :articleId ", nativeQuery = true)
    @Transactional
    void deleteById(UUID teacherId,UUID  articleId);


    @Modifying //know insert/update
    @Query(value ="insert into user_bookmarked_articles (app_user_id,bookmarked_article_id) " +
            "values (:teacherId,:articleId ) ", nativeQuery = true)
    @Transactional //commit data
    void bookmark( UUID teacherId, UUID articleId);

    @Query(value ="select distinct a.* from articles a  " +
            "inner join user_bookmarked_articles b on b.bookmarked_article_id = a.id " +
            " where b.app_user_id= :teacherId ", nativeQuery = true)
    Article findByTeacherId(UUID teacherId);


    @Query(value ="select distinct a.* from articles a  " +
            "inner join user_bookmarked_articles b on b.bookmarked_article_id = a.id " +
            " where b.bookmarked_article_id= :articleId", nativeQuery = true)
    Article findByArticleId(UUID articleId);
}
