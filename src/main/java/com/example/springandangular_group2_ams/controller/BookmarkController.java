package com.example.springandangular_group2_ams.controller;


import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import com.example.springandangular_group2_ams.model.request.BookmarkRequest;
import com.example.springandangular_group2_ams.model.response.ArtcileResponse;
import com.example.springandangular_group2_ams.model.response.PageResponse;
import com.example.springandangular_group2_ams.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;



    //add bookmark
    @PostMapping("/bookmarks/user/{id}")
    @Operation(summary = "Create BookmarkArticle  ")
    public ArtcileResponse<ArticleDto> bookmarks(@PathVariable("id") UUID id, @RequestBody BookmarkRequest bookmarkRequest) {


        //create bookmark
        bookmarkService.bookmarks(id, bookmarkRequest.getArticleId());

        //to check whether result was insert ot not
        var payload = bookmarkService.findById(id,bookmarkRequest.getArticleId());
        //check result of finding
        System.out.println("payload : "+payload);

        return payload;
    }






    //get all bookmark by app_user_id
    @GetMapping("/bookmarks/user/{id}")
    @Operation(summary = "Get BookmarkArticle  ")
    public PageResponse<?> fetchBookmark(@PathVariable("id") UUID id,
                                         @RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "5") Integer size
                                        ){


        return bookmarkService.fetchBookmark( id, page, size);


    }





    @DeleteMapping ("/bookmarks/user/{id}")
    @Operation(summary = "Delete BookmarkArticle  ")
    public ArtcileResponse<ArticleDto> deleteBookmark(@PathVariable("id") UUID teacherId,
                                         @RequestParam UUID articleId
    ){


        return bookmarkService.deleteBookmark( teacherId, articleId);


    }
























}
