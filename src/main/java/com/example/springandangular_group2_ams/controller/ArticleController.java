package com.example.springandangular_group2_ams.controller;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import com.example.springandangular_group2_ams.model.response.ErrorResponse;
import com.example.springandangular_group2_ams.model.response.PageResponse;
import com.example.springandangular_group2_ams.model.response.SuccessResponse;
import com.example.springandangular_group2_ams.service.ArticleService;
import lombok.AllArgsConstructor;
//import lombok.var;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/{id}")
    public SuccessResponse<Object> fetchById(@PathVariable UUID id){

        var payload = articleService.findById(id);

//        if (payload!=null){
//            var res = new SuccessResponse<>();
//            res.setMessage("Successfully fetch article");
//            res.setStatus("200");
//            res.setPayload(payload);
//            return res;
//        } else {
//            var res = new SuccessResponse<>();
//            res.setMessage("Failed");
//            res.setStatus("400");
//            return res;
//        }
        var res = new SuccessResponse<>();
            res.setMessage("Successfully fetch article");
            res.setStatus("200");
            res.setPayload(payload);
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
        res.getTotalPages();
        res.getTotalElements();
        return res;

    }
}
