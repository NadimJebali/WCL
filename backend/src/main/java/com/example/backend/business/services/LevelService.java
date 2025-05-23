package com.example.backend.business.services;

import java.util.List;

import com.example.backend.dao.entities.Level;
import com.example.backend.web.Models.dto.LevelDto;

public interface LevelService {

    LevelDto addLevel(LevelDto levelDto);
    LevelDto getLevelDtoById(Long id) ;
    Level getLevelById(Long id);
    List<LevelDto> getAllLevel();

    void deleteLevelById(Long id);
}
