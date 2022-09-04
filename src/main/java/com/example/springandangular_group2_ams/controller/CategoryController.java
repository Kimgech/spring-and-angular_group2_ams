package com.example.springandangular_group2_ams.controller;

import com.example.springandangular_group2_ams.model.dto.CategoryDto;
import com.example.springandangular_group2_ams.model.request.CategoryRequest;
import com.example.springandangular_group2_ams.model.response.ErrorResponse;
import com.example.springandangular_group2_ams.model.response.PageResponse;
import com.example.springandangular_group2_ams.model.response.SuccessResponse;
import com.example.springandangular_group2_ams.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @GetMapping()
    public PageResponse<?> getAllCategories(
            @RequestParam (defaultValue = "1")Integer page,
            @RequestParam (defaultValue = "5")Integer size
    ){
        var payload = categoryService.fetchAll(page-1, size);

        var res = new PageResponse<>();
        res.setMessage("successfully fetched categories");
        res.setStatus("200");
        res.setPayload(payload.getContent());
        res.setPage(payload.getNumber()+1);
        res.setSize(payload.getSize());
        res.setTotalPages(payload.getTotalPages());
        res.setTotalElements(payload.getTotalElements());
        return res;
    }

    @GetMapping("/{id}")
    public SuccessResponse<?> getCategoryById(@PathVariable Long id){

        var res = new SuccessResponse<>();

        try {
            var payload = categoryService.fetchById(id);
            if (id != null){
                res.setMessage("successfully fetched category with id : "+id);
                res.setStatus("200");
                res.setPayload(payload);
            }
        }catch(Exception e){
            res.setMessage(e.getMessage());
            res.setStatus("500");
        }
        return res;
    }

    @PostMapping
    public SuccessResponse<?> createNewCategory(@RequestBody CategoryRequest categoryRequest){
        var result = new SuccessResponse<>();
        try{
            if (categoryRequest.getName().isEmpty()){
                result.setMessage("Hello");
                result.setStatus("500");
            }else{
                var payload = categoryService.newCategory(categoryRequest);
                result.setMessage("Created category successfully");
                result.setStatus("200");
                result.setPayload(payload);
            }
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setStatus("500");
        }
       return result;
    }

    @PutMapping("/{id}")
    public SuccessResponse<?> updateCategory(@RequestBody CategoryRequest categoryRequest, @PathVariable Long id){
        var catRes = new SuccessResponse<>();
        try {
            if (id != null){
                var payload = categoryService.updateCategory(id, categoryRequest);
                catRes.setMessage("Successfully updated category with id : "+id);
                catRes.setStatus("200");
                catRes.setPayload(payload);
            }
        }catch (Exception e){

            catRes.setMessage(e.getMessage());
            catRes.setStatus("500");
        }

        return catRes;
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<?> deleteCategory(@PathVariable Long id){
        var catRes = new SuccessResponse<>();
        try {
            if(id != null){
                categoryService.delete(id);
                catRes.setMessage("deleted category with id: "+ id);
                catRes.setStatus("200");
            }
        }catch (Exception e){
            catRes.setMessage(e.getMessage());
            catRes.setStatus("500");

        }
        return catRes;
    }

    @GetMapping("/name")
    public PageResponse<?> findAllByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ){

        var payload = categoryService.searchByName(name,page-1, size);

        var result = new PageResponse<>();
        result.setMessage("successfully fetched categories");
        result.setStatus("200");
        result.setPayload(payload.getContent());
        result.setPage(page);
        result.setSize(size);
        result.setTotalPages(payload.getTotalPages());
        result.setTotalElements(payload.getTotalElements());
        return result;
    }
}
