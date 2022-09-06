package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import com.example.springandangular_group2_ams.model.entities.Article;
import com.example.springandangular_group2_ams.model.response.ArtcileResponse;
import com.example.springandangular_group2_ams.model.response.PageResponse;
import com.example.springandangular_group2_ams.repository.AppUserRepository;
import com.example.springandangular_group2_ams.repository.BookmarkRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BookmarkServiceImp implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    private final AppUserRepository  appUserRepository;

    // create bookmarks
    @Override
    public void bookmarks(UUID teacherId, UUID articleId) {
        var res = new ArtcileResponse<ArticleDto>();

        try {
            bookmarkRepository.bookmark(teacherId, articleId);

            if (!bookmarkRepository.findById(teacherId, articleId).toDto().equals(null)) {
                bookmarkRepository.deleteById(teacherId, articleId);
                bookmarkRepository.bookmark(teacherId, articleId);
            }
        } catch (Exception exception) {
        }
    }


    //find articleBookmark by teacherId & articleId
    @Override
    public ArtcileResponse<ArticleDto> findById(UUID teacherId, UUID articleId) {
        var res = new ArtcileResponse<ArticleDto>();
        try {
            res.setPayload(bookmarkRepository.findById(teacherId, articleId).toDto());
            res.setStatus("201");
            res.setMessage("added bookmark successfully for user: " + teacherId);
            return res;

        } catch (Exception exception) {

            var teacher_Id = appUserRepository.findById(teacherId);
            var article_Id = bookmarkRepository.findById(articleId);

            if(teacher_Id.isPresent() ){
                System.out.println("teacher:"+teacher_Id);
                res.setStatus("206");
                res.setMessage("cannot find article id: " +teacher_Id);
            }else if (article_Id.isPresent() ){
                System.out.println("articleId:"+article_Id);
                res.setStatus("206");
                res.setMessage("cannot find user id: " +articleId);
            }else{
                res.setStatus("206");
                res.setMessage("cannot article id: " +articleId +" cannot find user id: " +teacher_Id);
            }
            return res;
        }
    }


    //get bookmark with pagination by teacherId
    @Override
    public Page<ArticleDto> findAllById(UUID id, Integer page, Integer size) {
        //get pagination following page a& size
        var pageRequest = PageRequest.of(page, size);
        var result = bookmarkRepository.findAllById(id, pageRequest);
        //return a new Page with the content of the current one mapped by the given Function
        return result.map(Article::toDto);


    }

    //get all bookmark by app_user_id with pagination
    @Override
    public PageResponse<?> fetchBookmark(UUID id, Integer page, Integer size) {
        var res = new PageResponse<>();
        var payload = findAllById(id, page - 1, size);

        try {
            // check size and page > 0
            if (size > 0 || page > 0) {
                res.setMessage("successfully fetched bookmarks of user: " + id);
                res.setStatus("200");
                res.setPayload(payload.getContent());
                // check size
                if (page <= payload.getTotalPages()) { //current page <= page of database table

                    if (page == payload.getTotalPages()) {//current page = page of database table

                        res.setSize(((int) payload.getTotalElements() - (size * (page - 1))));
                    } else {//current page != page of database table
                        res.setSize(payload.getSize());
                    }
                } else {//current page > page of database table

                    res.setSize(size - payload.getSize());
                }

                res.setPage(page);
                res.setTotalPages(payload.getTotalPages());
                res.setTotalElements(payload.getTotalElements());

            } else { // size || page < 0
                res.setMessage("java.lang.IllegalStateException: page cannot be smaller than 1");
                res.setStatus("500");
            }
        } catch (Exception e) {
            res.setMessage("cannot find user id:" + id);
            res.setStatus("206");
        }
        return res;
    }

    @Override
    public ArtcileResponse<ArticleDto> deleteBookmark(UUID id, UUID articleId) {
        var res = new ArtcileResponse<ArticleDto>();
        try {
            if (!bookmarkRepository.findById(id, articleId).toDto().equals(null)) {
                bookmarkRepository.deleteById(id, articleId);
                res.setStatus("200");
                res.setMessage("successfully removed article id:" + id + " from bookmarks");

            }

            return res;

        }catch (Exception e){
            var teacherId = bookmarkRepository.findByTeacherId(id);
            var article_Id = bookmarkRepository.findByArticleId(articleId);
            if(teacherId !=null ){
                System.out.println("teacher:"+teacherId.toDto());
                res.setStatus("206");
                res.setMessage("can not find article id: " +id);

            }else if (article_Id != null ){
                System.out.println("articleId:"+article_Id.toDto());
                res.setStatus("206");
                res.setMessage("can not find user id: " +articleId);
            }else{
                res.setStatus("206");
                res.setMessage("can not article id: " +articleId +" can not find user id:" +id);
            }

            return res;


        }









//




    }

}
