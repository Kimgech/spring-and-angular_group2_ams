package com.example.springandangular_group2_ams.model.dto;

import com.example.springandangular_group2_ams.model.entities.Article;
import com.example.springandangular_group2_ams.model.entities.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto implements Serializable{
        private UUID id;
        private String name;
        private Role role;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<Article> bookmark;

        public AppUserDto(UUID id, String name, Role role) {
                this.id = id;
                this.name = name;
                this.role = role;
        }
}
