package com.example.backend.web.Models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    private Long id ;
    private String name ;
    private LevelDto levelDto ;
}
