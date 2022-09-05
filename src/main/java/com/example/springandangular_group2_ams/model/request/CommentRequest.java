package com.example.springandangular_group2_ams.model.request;

import com.example.springandangular_group2_ams.model.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentRequest {
    private String caption;

    public Comment toEntity(){
        var comment = new Comment();
        comment.setCaption(this.caption);
        return comment;
    }

}
