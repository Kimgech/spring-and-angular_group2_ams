package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import com.example.springandangular_group2_ams.model.entities.Article;
import com.example.springandangular_group2_ams.model.response.ArtcileResponse;
import com.example.springandangular_group2_ams.model.response.PageResponse;
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



    // create bookmarks
    @Override
    public void bookmarks(UUID teacherId,UUID articleId ) {

            bookmarkRepository.bookmark(teacherId,articleId);
    }





    //find articleBookmark by teacherId & articleId
    @Override
    public ArtcileResponse<ArticleDto> findById(UUID teacherId, UUID articleId) {
        var res = new ArtcileResponse<ArticleDto>();
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







    //get bookmark with pagination by teacherId
    @Override
    public Page<ArticleDto> findAllById(UUID id, Integer page, Integer size) {


            //get pagination following page a& size
            var pageRequest = PageRequest.of(page, size);
            var result = bookmarkRepository.findAllById(id,pageRequest);
            //return a new Page with the content of the current one mapped by the given Function
            return result.map(Article::toDto);


    }








    //get all bookmark by app_user_id with pagination
    @Override
    public PageResponse<?> fetchBookmark(UUID id, Integer page, Integer size) {

        var res = new PageResponse<>();

        var payload = findAllById(id,page - 1, size);


        try{
            // check size and page > 0
            if(size > 0 || page > 0){

                res.setMessage("successfully fetched bookmarks of user: "+id);
                res.setStatus("200");
                res.setPayload(payload.getContent());

                // check size
                if(page <= payload.getTotalPages()){ //current page <= page of database table

                    if (page == payload.getTotalPages()) {//current page = page of database table

                        res.setSize(((int) payload.getTotalElements() - (size * (page - 1))));
                    }else {//current page != page of database table
                        res.setSize(payload.getSize());
                    }

                }else{//current page > page of database table

                    res.setSize(size - payload.getSize());

                }

                res.setPage(page);
                res.setTotalPages(payload.getTotalPages());
                res.setTotalElements(payload.getTotalElements());

            }else { // size || page < 0
                res.setMessage("java.lang.IllegalStateException: page cannot be smaller than 1");
                res.setStatus("500");
            }
        }catch (Exception e){
            res.setMessage("cannot find user id:"+id);
                res.setStatus("206");
        }

        return res;

    }










}
