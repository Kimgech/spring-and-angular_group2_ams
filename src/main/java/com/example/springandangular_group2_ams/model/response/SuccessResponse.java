package com.example.springandangular_group2_ams.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuccessResponse<T> {
    private String message;
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;

    public SuccessResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
