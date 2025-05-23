package com.example.backend.web.Models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LevelDto {

    private Long id ;
    private String name ;
    private CategoryDto categoryDto ;

}
