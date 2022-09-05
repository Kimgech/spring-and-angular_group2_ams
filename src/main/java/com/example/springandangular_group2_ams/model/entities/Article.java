package com.example.springandangular_group2_ams.model.entities;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Article")
@Table(name = "articles")
@Getter
@Setter
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "is_published")
    private Boolean isPublished;


    @OneToMany(mappedBy = "article")
    private List<Comment> commentList = new ArrayList<>();



    @ManyToMany
    @JoinTable(name = "article_categories",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;



    @ManyToOne
    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    private AppUser user;





    public ArticleDto toDto() {
        return new ArticleDto(
                this.id,
                this.title,
                this.description,
                this.user.toDto()
        );
    }


}
