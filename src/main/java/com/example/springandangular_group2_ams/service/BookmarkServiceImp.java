package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import com.example.springandangular_group2_ams.model.entities.Article;
import com.example.springandangular_group2_ams.model.response.ArtcileResponse;
import com.example.springandangular_group2_ams.repository.AppUserRepository;
import com.example.springandangular_group2_ams.repository.BookmarkRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BookmarkServiceImp implements BookmarkService{

    private final BookmarkRepository bookmarkRepository;

    private final AppUserRepository appUserRepository;


    @Override
    public void bookmarks(UUID teacherId,UUID articleId ) {
        try {
            appUserRepository.bookmark(teacherId,articleId);
        }catch (Exception exception){

        }
    }




    @Override
    public ArtcileResponse<?> findById(UUID teacherId, UUID articleId) {
        var res = new ArtcileResponse<>();
        try {
            res.setPayload(bookmarkRepository.findById(teacherId,articleId).toDto());
            res.setStatus("200");
            res.setMessage("successful find article");
            return  res;
        }catch (Exception exception){
           res.setStatus("500");
           res.setMessage("can not find article");
           return  res;
        }
    }




    @Override
    public Page<ArticleDto> findAllById(UUID id, Integer page, Integer size) {

        try {
            var pageRequest = PageRequest.of(page, size);
            var result = bookmarkRepository.findAllById(id,pageRequest);
            return result.map(Article::toDto);
        }catch (Exception exception){
            return null;
        }

    }



}
