package com.example.springandangular_group2_ams.controller;

import com.example.springandangular_group2_ams.model.dto.CommentDto;
import com.example.springandangular_group2_ams.model.request.ArticleRequest;
import com.example.springandangular_group2_ams.model.request.CommentRequest;
import com.example.springandangular_group2_ams.model.response.PageResponse;
import com.example.springandangular_group2_ams.model.response.SuccessResponse;
import com.example.springandangular_group2_ams.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    //post new article
    @PostMapping
    public SuccessResponse<?> createPost(@RequestBody ArticleRequest articleRequest){
        var response = new SuccessResponse<>();
        try {
            var payload = articleService.createPost(articleRequest);
            System.out.println(payload.toString());
            return new SuccessResponse<>(
                    "create article successfully",
                    "201",
                    payload
            );
        }catch (Exception e){
            response.setMessage("cannot find teacher with id "+articleRequest.getUserId());
            response.setStatus("500");
            return response;
        }
    }

    //update article by id
    @PutMapping("/{id}")
    public SuccessResponse<?> updatePost(@PathVariable UUID id, @RequestBody ArticleRequest articleRequest){
        var response = new SuccessResponse<>();
        try {
            var payload = articleService.updatePost(id,articleRequest);
            if (payload !=null){
                response.setMessage("Update article successfully");
                response.setStatus("201");
                response.setPayload(payload);
            }else {
                response.setMessage("Can not find article with id "+id);
                response.setStatus("500");
            }
            return response;
        }catch (Exception e){
            response.setMessage(e.getMessage());
            response.setStatus("500");
            return response;
        }
    }

    //fetch article by id
    @GetMapping("/{id}")
    public SuccessResponse<?> fetchArticleById(@PathVariable UUID id){
        var res = new SuccessResponse<>();
        try {
            var payload = articleService.findArticleById(id);
            if (id!=null){
                res.setMessage("successfully fetched article id: "+ id);
                res.setStatus("200");
                res.setPayload(payload);
            }
        }catch (Exception e){
            res.setMessage(e.getMessage());
            res.setStatus("206");
        }
        return res;
    }

    //fetch all articles
    @GetMapping
    public PageResponse<?> fetchAllArticle(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ){
        var res = new PageResponse<>();
        try{
            var payload = articleService.fetchAllArticles(page -1, size);
            if (page>0 || size>0){
                res.setMessage("successfully fetched all articles");
                res.setStatus("200");
                res.setPayload(payload.getContent());
                if(page <= payload.getTotalPages()){
                    if (page == payload.getTotalPages()){
                        res.setSize(((int) payload.getTotalElements()-(size*(page -1))));
                    }else {
                        res.setSize(payload.getSize());
                    }
                }else{
                    res.setSize(0);
                }
                res.setPage(page);
                res.setTotalPages(payload.getTotalPages());
                res.setTotalElements(payload.getTotalElements());
            }
        }catch (Exception e){
            res.setMessage(e.getMessage());
            res.setStatus("500");
        }
        return res;
    }

    //delete article by id
    @DeleteMapping("/{id}")
    public SuccessResponse<?> delete(@PathVariable UUID id){
        var res = new SuccessResponse<>();
        try{
            if(id!=null){
                articleService.delete(id);
                res.setMessage("deleted article id: " + id );
                res.setStatus("200");
            }
        }catch (Exception e){
            res.setMessage(e.getMessage());
            res.setStatus("500");
        }
        return res;
    }

    //post comment on article by id
    @PostMapping("/{id}/comments")
    public SuccessResponse<?> addComments(@PathVariable UUID id, @RequestBody CommentRequest commentRequest){
        var response = new SuccessResponse<>();
        try {
            var payload = articleService.addComments(id, commentRequest);
            response.setMessage("successfully add comments");
            response.setStatus("201");
            response.setPayload(payload);
            return response;
        }catch (Exception e){
            response.setMessage(e.getMessage());
            response.setStatus("500");
            return response;
        }
    }

    //get article comments by id
    @GetMapping("/{id}/comments")
    public SuccessResponse<?> getCommentByArticleId(@PathVariable UUID id){
        var response = new SuccessResponse<>();
        try{
            var payload = articleService.fetchCommentByArticleId(id);
            response.setMessage("successfully fetched comments article id: " + id);
            response.setStatus("201");
            response.setPayload(payload);
            return response;
        }catch (Exception e){
            response.setMessage(e.getMessage());
            response.setStatus("500");
            return response;
        }
    }

    //fetch article by is published
    @GetMapping("/published")
    public PageResponse<?> fetchArticlesByIsPublished(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ){
        var res = new PageResponse<>();
        try {
            var payload = articleService.findAllByIsPublished(page -1,size);
            if(page>0 || size>0){
                res.setMessage("successfully fetched all articles that is published");
                res.setStatus("200");
                res.setPayload(payload.getContent());
                res.setPage(page);
                res.setSize(size);
                res.setTotalPages(payload.getTotalPages());
                res.setTotalElements(payload.getTotalElements());
            }
        }catch (Exception e){
            res.setMessage(e.getMessage());
            res.setStatus("500");
        }
        return res;
    }
}
















