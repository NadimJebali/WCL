package com.example.backend.business.services;


import com.example.backend.dao.entities.Solution;
import com.example.backend.web.Models.dto.SolutionDto;

public interface SolutionService {
    
    SolutionDto addSolutionDto(SolutionDto solutionDto);
    SolutionDto findSolutionDtoById(Long id);
    Solution findSolutionById(Long id);
    void deletesolution(Long id);
}
