package com.example.springandangular_group2_ams.model.entities;

import com.example.springandangular_group2_ams.model.dto.AppUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "AppUser")
@Table(name = "app_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppUser {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )    private UUID id;

    @Column(unique = true, name = "name", nullable = false)
    private String name;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Article> articles = new ArrayList<>();


    @ManyToMany
    @JoinTable(name = "user_bookmarked_articles",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "bookmarked_article_id")
    )
    private List<Article> bookmark;

    public AppUser(UUID id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }


    public AppUserDto toDto(){
        return new AppUserDto(this.id, this.name, this.role);
    }

}
