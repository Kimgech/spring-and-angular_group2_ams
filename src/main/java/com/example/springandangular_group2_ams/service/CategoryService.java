package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.CategoryDto;
import com.example.springandangular_group2_ams.model.request.CategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Page<CategoryDto> fetchAll(Integer page, Integer size);

    CategoryDto fetchById(Long id);

    CategoryDto newCategory(CategoryRequest categoryRequest);

    CategoryDto updateCategory(Long id, CategoryRequest categoryRequest);

    Boolean delete(Long id);

    Page<CategoryDto> searchByName(String name, Integer page, Integer size);

}
