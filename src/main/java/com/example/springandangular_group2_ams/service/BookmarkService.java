package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import com.example.springandangular_group2_ams.model.response.ArtcileResponse;
import com.example.springandangular_group2_ams.model.response.PageResponse;
import org.springframework.data.domain.Page;


import java.util.UUID;

public interface BookmarkService {

 //create bookmark
 void bookmarks(UUID teacherId, UUID articleId);

 //find articleBookmark by teacherId & articleId
 ArtcileResponse<ArticleDto> findById(UUID teacherId, UUID articleId);

 //get bookmark with pagination by teacherId
 Page<ArticleDto> findAllById(UUID id, Integer page, Integer size);


 //get all bookmark by app_user_id
 PageResponse<?> fetchBookmark( UUID id,
                                Integer page,
                                Integer size);

 ArtcileResponse<ArticleDto> deleteBookmark(UUID id, UUID articleId);

}
