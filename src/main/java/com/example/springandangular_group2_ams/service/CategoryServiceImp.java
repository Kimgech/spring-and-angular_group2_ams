package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.dto.CategoryDto;
import com.example.springandangular_group2_ams.model.entities.Category;
import com.example.springandangular_group2_ams.model.request.CategoryRequest;
import com.example.springandangular_group2_ams.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService{

    private final CategoryRepository categoryRepository;
    @Override
    public Page<CategoryDto> fetchAll(Integer page, Integer size) {
        var pageRequest = PageRequest.of(page,size);
        var result = categoryRepository.findAll(pageRequest);
        return result.map(Category::toDto);
    }
    @Override
    public CategoryDto fetchById(Long id){
        var response = categoryRepository.findById(id);
        return response.get().toDto();
    }

    @Override
    public CategoryDto newCategory(CategoryRequest categoryRequest) {
        var category = categoryRequest.toEntity();
        var saveCategory = categoryRepository.save(category);
        return saveCategory.toDto();
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryRequest categoryRequest) {
            var catEntity = categoryRequest.toEntity(id);
            return categoryRepository.save(catEntity).toDto();
    }

    @Override
    public Boolean delete(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<CategoryDto> searchByName(String name
            , Integer page, Integer size
    ) {
        var pageRequest = PageRequest.of(page,size);
        return categoryRepository.findAllByNameContaining(name , pageRequest);

    }
}
