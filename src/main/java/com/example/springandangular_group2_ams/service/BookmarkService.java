package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.AppUserDto;
import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import com.example.springandangular_group2_ams.model.dto.BookmarkDto;
import com.example.springandangular_group2_ams.model.entities.Article;
import com.example.springandangular_group2_ams.model.request.BookmarkRequest;
import com.example.springandangular_group2_ams.model.response.ArtcileResponse;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BookmarkService {

 void bookmarks(UUID teacherId, UUID articleId);

 ArtcileResponse<?> findById(UUID teacherId, UUID articleId);

 Page<ArticleDto> findAllById(UUID id, Integer page, Integer size);



}
