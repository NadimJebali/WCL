package com.example.backend.web.Models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciceDto {
    
    private Long id ;
    private String title ;
    private String description ;
    private String content ;
    private String image ;
    private LevelDto levelDto ;
    private SubjectDto subjectDto ;
    private SolutionDto solutionDto ;
    private UserDto userDto ;
}
