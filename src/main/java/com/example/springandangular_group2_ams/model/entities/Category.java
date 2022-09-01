package com.example.springandangular_group2_ams.model.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Category")
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name" , length = 100)
    private String name;
}
