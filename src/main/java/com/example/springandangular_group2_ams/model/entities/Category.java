package com.example.springandangular_group2_ams.model.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Category")
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name" , length = 100, nullable = false)
    private String name;

}
