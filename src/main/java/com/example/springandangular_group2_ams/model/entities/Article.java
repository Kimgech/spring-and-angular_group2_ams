package com.example.springandangular_group2_ams.model.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Article")
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "isPublished")
    private Boolean isPublished;

    @Column(name = "comment")
    private String comment;

}
