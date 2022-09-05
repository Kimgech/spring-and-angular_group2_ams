package com.example.springandangular_group2_ams.controller;


import com.example.springandangular_group2_ams.model.request.BookmarkRequest;
import com.example.springandangular_group2_ams.model.response.ArtcileResponse;
import com.example.springandangular_group2_ams.model.response.PageResponse;
import com.example.springandangular_group2_ams.service.BookmarkService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;



    @PostMapping("/bookmarks/user/{id}")
    public ArtcileResponse<?> bookmarks(@PathVariable("id") UUID id, @RequestBody BookmarkRequest bookmarkRequest) {
        bookmarkService.bookmarks(id, bookmarkRequest.getArticleId());
        var payload = bookmarkService.findById(id,bookmarkRequest.getArticleId());
        System.out.println("payload : "+payload);

        return payload;
    }






    @GetMapping("/bookmarks/user/{id}")
    public PageResponse<?> fetchBookmark(@PathVariable("id") UUID id,
                                         @RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "5") Integer size
                                        ){
        var res = new PageResponse<>();

        try {
            var payload = bookmarkService.findAllById(id,page - 1, size);

            if(!payload.isEmpty()){
                res.setPayload(payload.getContent());
                res.setTotalPages(payload.getTotalPages());
                res.setTotalElements(payload.getTotalElements());
                res.setPage(payload.getNumber());
                res.setSize(payload.getSize());
                res.setMessage("successfully fetched bookmarks of user: "+id);
                res.setStatus("200");

                return  res;
            }else {
                res.setMessage("cannot find user id:"+id);
                res.setStatus("206");
                return res;
            }

        }catch (Exception e){
            res.setMessage("cannot find user id:"+id);
            res.setStatus("206");
            return res;
        }




    }
























}
