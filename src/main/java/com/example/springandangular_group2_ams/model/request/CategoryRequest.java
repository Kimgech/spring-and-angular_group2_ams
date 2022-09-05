package com.example.springandangular_group2_ams.model.request;

import com.example.springandangular_group2_ams.model.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    private String name;

    public Category toEntity(){
        var category = new Category(null, this.name);
        return category;
    }
    public Category toEntity(Long id){
        var category = new Category(id, this.name);
        return category;
    }

}
