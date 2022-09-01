package com.example.springandangular_group2_ams.model.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Article")
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "is_published")
    private Boolean isPublished;

    @ManyToOne
    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    private AppUser user;


    @OneToMany(mappedBy = "article")
    private List<Comment> commentList = new ArrayList<>();



    @ManyToMany
    @JoinTable(name = "article_categories",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categoryList;


}
