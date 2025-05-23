package com.example.backend.web.Models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciceNDto {
    
    private Long id ;
    private String title ;
    private String description ;
    private String content ;
    private String image ;
    private Long levelDtoId ;
    private Long subjectDtoId ;
    private Long solutionDtoId ;
    private Long userDtoId ;
}
