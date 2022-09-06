package com.example.springandangular_group2_ams.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkDto implements Serializable {

    private UUID appUserId;
    private UUID articleId;

}
