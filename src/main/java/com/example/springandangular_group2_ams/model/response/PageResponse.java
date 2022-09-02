package com.example.springandangular_group2_ams.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResponse<T> {
    private String message;
    private String status;
    private T payload;
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Long totalElements;
}