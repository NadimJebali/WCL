package com.example.backend.business.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.business.services.SolutionService;
import com.example.backend.dao.entities.Solution;
import com.example.backend.dao.repositories.SolutionRepository;
import com.example.backend.exception.modelsexception.NotFound_404;
import com.example.backend.web.Models.dto.SolutionDto;
import com.example.backend.web.Models.mapper.SolutionMapper;

@Service
public class SolutionServiceImpl implements SolutionService {

    @Autowired
    private SolutionRepository solutionRepository ;

    @Autowired
    private SolutionMapper solutionMapper ;

    

    @Override
    public SolutionDto addSolutionDto(SolutionDto solutionDto) {
        Solution solution = this.solutionRepository.save(this.solutionMapper.toEntity(solutionDto));
        return this.solutionMapper.toDto(solution);
    }

    @Override
    public SolutionDto findSolutionDtoById(Long id) {
        return this.solutionRepository.findById(id)
                                        .map(this.solutionMapper :: toDto)
                                        .orElseThrow(() -> new NotFound_404("Solution Not Found"));
    }

    @Override
    public void deletesolution(Long id) {
        Solution solution = this.solutionRepository.findById(id)
                                                    .orElseThrow(() -> new NotFound_404("solution Not Found"));
        this.solutionRepository.delete(solution);
        
    }

    @Override
    public Solution findSolutionById(Long id) {
         return this.solutionRepository.findById(id)
                                        .orElseThrow(() -> new NotFound_404("Solution Not Found"));
    }


    
    
}
