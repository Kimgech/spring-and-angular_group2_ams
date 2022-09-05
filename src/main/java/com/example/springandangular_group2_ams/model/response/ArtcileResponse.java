package com.example.springandangular_group2_ams.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ArtcileResponse<T> {
    private Object message;
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;
}

