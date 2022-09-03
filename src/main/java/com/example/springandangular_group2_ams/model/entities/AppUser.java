package com.example.springandangular_group2_ams.model.entities;

import com.example.springandangular_group2_ams.model.dto.AppUserDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "AppUser")
@Table(name = "app_users")
public class AppUser {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Article> articles;


    @ManyToMany
    @JoinTable(name = "user_bookmarked_articles",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "bookmarked_article_id")
    )
    private List<Article> bookmark;

    public AppUserDto toDto(){
        return new AppUserDto(this.id,this.name,this.role);
    }
}
