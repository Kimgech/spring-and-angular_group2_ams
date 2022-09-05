package com.example.springandangular_group2_ams.model.entities;

import com.example.springandangular_group2_ams.model.dto.ArticleDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
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
    private Boolean isPublished = false;

    @ManyToOne
    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    private AppUser user;


    @OneToMany(mappedBy = "article")
    private List<Comment> commentList;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "article_categories",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categoryList;

    public ArticleDto toDto(){
        return new ArticleDto(
                this.id,
                this.title,
                this.description,
                this.isPublished,
                this.categoryList.stream()
                        .map(Category::toDto)
                        .collect(Collectors.toList()),
                this.user.toDto(),
                this.commentList.stream()
                        .map(Comment::toDto)
                        .collect(Collectors.toList())
        );
    }
//    public ArticleDto toDto(){
//        return new ArticleDto(
//                this.id,
//                this.title,
//                this.description,
//                this.isPublished,
//                this.categoryList.stream()
//                        .map(Category::toDto)
//                        .collect(Collectors.toList()),
//                this.user.toDto()
//        );
//    }
}
