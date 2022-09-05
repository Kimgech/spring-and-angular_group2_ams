package com.example.springandangular_group2_ams.repository;

import com.example.springandangular_group2_ams.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
//    Category findByName(String name);
    List<Category> findByName(String name);
    List<Category> findAllByNameContaining(String name);
}
