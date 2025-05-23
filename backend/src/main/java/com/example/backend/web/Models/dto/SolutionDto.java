package com.example.backend.web.Models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolutionDto {
    
    private long id ;
    private String title ;
    private String content ;
    //private ExerciceDto exerciceDto ;
}
