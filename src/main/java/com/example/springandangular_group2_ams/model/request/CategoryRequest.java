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
        var category = new Category();
        category.setName(this.name);
        return category;
    }

    public Category toEntity(Long id){
        return new Category(id, this.name);
    }
}
