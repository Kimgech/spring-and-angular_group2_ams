package com.example.springandangular_group2_ams.model.entities;

import com.example.springandangular_group2_ams.model.dto.CommentDto;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name="Comment")
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "caption")
    private String caption;

    @ManyToOne
    @JoinColumn(name = "article_id",referencedColumnName = "id")
    private Article article;

    public CommentDto toDto(){
        return new CommentDto(this.id,this.caption);
    }

}
