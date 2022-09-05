package com.example.springandangular_group2_ams.controller;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
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

    @PostMapping
    public SuccessResponse<?> createPost(@RequestBody ArticleRequest articleRequest){

        try {
            var payload = articleService.createPost(articleRequest);
            System.out.println(payload.toString());
            return new SuccessResponse<>(
                    "Insert Article successfully",
                    "201",
                    payload
            );
        }catch (Exception e){
            var response = new SuccessResponse<>();
            response.setMessage("Can not find teacher with id "+articleRequest.getUserId());
            response.setStatus("500");
            return response;
        }
    }

    @PutMapping("/{id}")
    public ArticleDto updatePost(@PathVariable UUID id, @RequestBody ArticleRequest articleRequest){
        return articleService.updatePost(id,articleRequest);
    }

    @GetMapping("/{id}")
    public SuccessResponse<Object> fetchById(@PathVariable UUID id) {
        var payload = articleService.findById(id);
        var res = new SuccessResponse<>();
        try {
            if (payload != null) {
                res.setMessage("Successfully fetch article");
                res.setStatus("200");
                res.setPayload(payload);
            } else {
                res.setMessage("Failed");
                res.setStatus("400");
            }
        } catch (Exception e) {
            res.setMessage("Failed to fetch article");
            res.setStatus("200");
        }
        return res;

    }
    @GetMapping
    public PageResponse<?> fetchAllArticle(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ){
        var payload = articleService.fetch(page -1, size);
        var res = new PageResponse<>();
        res.setMessage("Successfully fetch all articles");
        res.setStatus("200");
        res.setPayload(payload.getContent());
        res.setPage(page);
        res.setSize(size);
        res.setTotalPages(payload.getTotalPages());
        res.setTotalElements(payload.getTotalElements());
        return res;
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<?> delete(@PathVariable UUID id){
        articleService.delete(id);
        var res = new SuccessResponse<>();
        res.setMessage("deleted article successfully");
        res.setStatus("200");
        return res;
    }

    @PostMapping("/{id}/comments")
    public CommentDto addComments(@PathVariable UUID id, @RequestBody CommentRequest commentRequest){
        return articleService.addComments(id, commentRequest);
    }

    @GetMapping("/{id}/comments")
    public SuccessResponse<?> getCommentByArticleId(@PathVariable UUID id){
        var payload = articleService.fetchCommentByArticleId(id);
        var res = new SuccessResponse<>();
            res.setMessage("successfully fetch comments");
            res.setStatus("200");
            res.setPayload(payload);
            return res;
    }

}














