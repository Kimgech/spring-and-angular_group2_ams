package com.example.springandangular_group2_ams.model.entities;

import com.example.springandangular_group2_ams.model.dto.AppUserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "AppUser")
@Table(name = "app_users")
@Getter
@Setter
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;



    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Article> articles = new ArrayList<>();


    @ManyToMany
    @JoinTable(name = "user_bookmarked_articles",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "bookmarked_article_id")
    )
    private List<Article> bookmarkArticle;


    public AppUserDto toDto(){
        return new AppUserDto(this.id, this.name,this.role);
    }

}
