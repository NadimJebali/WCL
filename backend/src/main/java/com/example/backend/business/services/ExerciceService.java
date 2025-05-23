package com.example.backend.business.services;

import java.util.List;

import com.example.backend.dao.entities.Exercice;
import com.example.backend.web.Models.dto.ExerciceDto;
import com.example.backend.web.Models.dto.ExerciceNDto;


public interface ExerciceService {

    List<ExerciceDto> AllExercices();
    ExerciceDto FindExerciceDtoByid(Long id);
    ExerciceDto AddExercice(ExerciceDto exerciceDTO);
    ExerciceDto AddExerciceN(ExerciceNDto exerciceNDto);
    ExerciceDto UpdateExercice(ExerciceDto newExerciceDTO , Long id);
    ExerciceDto UpdateExerciceN(ExerciceNDto exerciceNDto , Long id);
    void DeleteExercice(Long id);
    List<ExerciceDto>FindExerciceDtoByLevel(Long id);
    List<ExerciceDto>FindExerciceDtoBySubject(Long id);
    List<ExerciceDto>FindExerciceDtoByLevelAndSubject(Long idlevel , Long idsubject);

    Exercice FindExerciceByid(Long id) ;
    

}
