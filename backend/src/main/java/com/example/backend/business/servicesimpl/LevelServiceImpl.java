package com.example.backend.business.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.business.services.LevelService;
import com.example.backend.dao.entities.Level;

import com.example.backend.dao.repositories.LevelRepository;
import com.example.backend.exception.modelsexception.NotFound_404;
import com.example.backend.web.Models.dto.LevelDto;
import com.example.backend.web.Models.mapper.LevelMapper;

@Service
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelRepository levelRepository ;

    @Autowired
    private LevelMapper levelMapper ;

    



    @Override
    public LevelDto addLevel(LevelDto levelDto) {
        Level level = this.levelRepository.save(this.levelMapper.toEntity(levelDto));
        return this.levelMapper.toDto(level);
    }

    @Override
    public LevelDto getLevelDtoById(Long id) {
        return this.levelRepository.findById(id)
                                    .map(this.levelMapper :: toDto)
                                    .orElseThrow(() -> new NotFound_404("Level Not Found !"));
    }

    @Override
    public List<LevelDto> getAllLevel() {
        return this.levelRepository.findAll()
                                    .stream()
                                    .map(this.levelMapper :: toDto)
                                    .toList();

    }

    @Override
    public Level getLevelById(Long id) {
        return this.levelRepository.findById(id)
                                    .orElseThrow(() -> new NotFound_404("Level Not Found"));
    }

    @Override
    public void deleteLevelById(Long id) {
        Level level = getLevelById(id);
        this.levelRepository.delete(level);
    }
    
}
