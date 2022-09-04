package com.example.springandangular_group2_ams.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResponse<T> {
    private String message;
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer page;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer size;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalPages;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long totalElements;


//    public PageResponse(String message, String status) {
//        this.message = message;
//        this.status = status;
//    }
}
