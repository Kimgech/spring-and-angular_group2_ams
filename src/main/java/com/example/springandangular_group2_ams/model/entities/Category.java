package com.example.springandangular_group2_ams.model.entities;

import com.example.springandangular_group2_ams.model.dto.CategoryDto;

import com.example.springandangular_group2_ams.model.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Category")
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name" , length = 100, nullable = false)
    private String name;

    public Category(String name) {
    }


    public CategoryDto toDto(){
        return new CategoryDto(this.id, this.name);
    }
}
