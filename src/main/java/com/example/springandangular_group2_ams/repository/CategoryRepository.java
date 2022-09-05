package com.example.springandangular_group2_ams.repository;

import com.example.springandangular_group2_ams.model.dto.CategoryDto;
import com.example.springandangular_group2_ams.model.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByName(String name);
    Page<CategoryDto> findAllByNameContaining(String name, Pageable pageable);
}
