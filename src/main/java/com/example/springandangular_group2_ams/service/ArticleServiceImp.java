package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import com.example.springandangular_group2_ams.model.dto.CommentDto;
import com.example.springandangular_group2_ams.model.entities.Article;
import com.example.springandangular_group2_ams.model.entities.Category;
import com.example.springandangular_group2_ams.model.entities.Comment;
import com.example.springandangular_group2_ams.model.request.ArticleRequest;
import com.example.springandangular_group2_ams.model.request.CommentRequest;
import com.example.springandangular_group2_ams.repository.AppUserRepository;
import com.example.springandangular_group2_ams.repository.ArticleRepository;
import com.example.springandangular_group2_ams.repository.CategoryRepository;
import com.example.springandangular_group2_ams.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleServiceImp implements ArticleService{
    private final ArticleRepository articleRepository;
    private final AppUserRepository appUserRepository;
    private final CategoryRepository categoryRepository;

    private final CommentRepository commentRepository;

    @Override
    public ArticleDto createPost(ArticleRequest articleRequest) {
        var articleEntity = articleRequest.toEntity();
        var user = appUserRepository.findById(articleRequest.getUserId());
        var categories = new ArrayList<Category>();
        var cmt = new ArrayList<Comment>();

        if(user.isPresent()){
            System.out.println(user);
            if (Objects.equals(user.get().getRole(), "TEACHER")){
                for (String cat: articleRequest.getCategories()) {
                    var category  = categoryRepository.findByName(cat);
                    if (!category.isEmpty()){
                        categories.add(category.get(0));
                    }
                }
                System.out.println(categories);
                articleEntity.setUser(user.get());

                articleEntity.setCategoryList(categories);
                articleEntity.setCommentList(cmt);
                System.out.println(articleEntity);
                return articleRepository.save(articleEntity).toDto();
            }
            else return null;
        }
        throw new NoSuchElementException("Article not found");
    }

    @Override
    public ArticleDto updatePost(UUID id, ArticleRequest articleRequest) {
        var articleId = articleRepository.findById(id);
        var user = appUserRepository.findById(articleRequest.getUserId());
        var categories = new ArrayList<Category>();
        var cmt = new ArrayList<Comment>();
        for (String cat: articleRequest.getCategories()) {
            var category  = categoryRepository.findByName(cat);
            if (!category.isEmpty()){
                categories.add(category.get(0));
            }
        }

        if (articleId.isPresent() && user.isPresent()){
            var articleEntity = articleRequest.toEntity(id);
            articleEntity.setUser(user.get());
            articleEntity.setCategoryList(categories);
            articleEntity.setCommentList(cmt);
            return articleRepository.save(articleEntity).toDto();
        }
        throw new NoSuchElementException("Article not found");
    }

    @Override
    public List<CommentDto> fetchCommentByArticleId(UUID articleId) {
        var article = articleRepository.findById(articleId);
        if (article.isPresent()) {
            return article.get().toDto().getComments();
        }
        throw new NoSuchElementException("article not found");
    }

    @Override
    public CommentDto addComments(UUID id, CommentRequest commentRequest) {
        var articleId = articleRepository.findById(id);

        if (articleId.isPresent()) {
            var commentEntity = commentRequest.toEntity();
            commentEntity.setArticle(articleId.get());
            commentEntity.setCaption(commentRequest.getCaption());
            return commentRepository.save(commentEntity).toDto();
        }
        throw new NoSuchElementException("article not found");
    }

    @Override
    public Page<ArticleDto> findAllByIsPublished(Integer page, Integer size) {
        var pageRequest = PageRequest.of(page,size);
        var result = articleRepository.findAllByIsPublished(true,pageRequest);
//       result=result.stream().filter(Article::getIsPublished);
        return result.map(Article::toDto);
    }

    @Override
    public ArticleDto findArticleById(UUID articleId) {
        var article = articleRepository.findById(articleId);
//        if (article.isPresent()) {
//            return article.get().toDto();
//        }
//        else {
//            return null;
//        }
        return article.get().toDto();
    }

    @Override
    public Page<ArticleDto> fetchAllArticles(Integer page, Integer size) {
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








