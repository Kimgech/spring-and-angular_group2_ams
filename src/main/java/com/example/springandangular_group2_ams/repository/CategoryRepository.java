package com.example.springandangular_group2_ams.repository;

import com.example.springandangular_group2_ams.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
