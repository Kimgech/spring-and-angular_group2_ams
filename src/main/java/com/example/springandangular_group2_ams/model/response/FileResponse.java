package com.example.springandangular_group2_ams.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FileResponse<T> {
    private Object message;
    private String status;
    private T payload;
}
