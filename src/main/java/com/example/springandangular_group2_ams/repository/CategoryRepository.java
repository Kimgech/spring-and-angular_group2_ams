package com.example.springandangular_group2_ams.repository;

import com.example.springandangular_group2_ams.model.dto.CategoryDto;
import com.example.springandangular_group2_ams.model.entities.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<CategoryDto> findAllByNameContaining(String name, Pageable pageable);
}
