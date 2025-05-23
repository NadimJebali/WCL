package com.example.backend.business.servicesimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.business.services.ExerciceService;
import com.example.backend.business.services.LevelService;
import com.example.backend.business.services.SolutionService;
import com.example.backend.business.services.SubjectService;
import com.example.backend.business.services.UserService;
import com.example.backend.dao.entities.Exercice;
import com.example.backend.dao.entities.Level;
import com.example.backend.dao.entities.Subject;
import com.example.backend.dao.repositories.ExerciceRepository;
import com.example.backend.exception.modelsexception.NotFound_404;
import com.example.backend.web.Models.dto.ExerciceDto;
import com.example.backend.web.Models.dto.ExerciceNDto;
import com.example.backend.web.Models.mapper.ExerciceMapper;




@Service
public class ExerciceServiceImpl implements ExerciceService {

    @Autowired
    private ExerciceRepository exerciceRepository;

    @Autowired
    private ExerciceMapper exerciceMapper ;

    @Autowired
    private LevelService levelService ;

    @Autowired
    private SubjectService subjectService ;

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private UserService userService ;


    


    @Override
    public List<ExerciceDto> AllExercices() {

        return this.exerciceRepository.findAll()
                                        .stream()
                                        .map(this.exerciceMapper :: toDto )
                                        .toList();
    }

    @Override
    public ExerciceDto FindExerciceDtoByid(Long id) {

        return this.exerciceRepository.findById(id)
                                        .map(this.exerciceMapper :: toDto)
                                        .orElseThrow(() -> new NotFound_404("Exercice Not Found !")) ;
    }

    @Override
    public ExerciceDto AddExercice(ExerciceDto exerciceDTO) {
        Exercice exercice = this.exerciceRepository.save(this.exerciceMapper.toEntity(exerciceDTO));
        return this.exerciceMapper.toDto(exercice) ;
    }

    

    @Override
    public ExerciceDto UpdateExercice(ExerciceDto newExerciceDTO, Long id) {

        Exercice exercice = this.exerciceRepository.findById(id).orElse(null);
        exercice.setTitle(newExerciceDTO.getTitle());
        exercice.setDescription(newExerciceDTO.getDescription());
        exercice.setContent(newExerciceDTO.getContent());
        exercice.setImage(newExerciceDTO.getImage());
        Exercice newExercice = this.exerciceRepository.save(exercice);
        return this.exerciceMapper.toDto(newExercice) ;

    }

    @Override
    public void DeleteExercice(Long id) {
        Exercice exercice = FindExerciceByid(id);
        this.exerciceRepository.deleteById(id);
        this.solutionService.deletesolution(exercice.getSolution().getId());
        

    }

    @Override
    public List<ExerciceDto> FindExerciceDtoByLevel(Long id) {
        Level level = this.levelService.getLevelById(id);
        return this.exerciceRepository.findByLevel(level)
                                        .stream()
                                        .map(this.exerciceMapper :: toDto)
                                        .toList() ;
    }

    @Override
    public List<ExerciceDto> FindExerciceDtoBySubject(Long id) {
        Subject subject = this.subjectService.getSubjectById(id);
        return this.exerciceRepository.findBySubject(subject)
                                        .stream()
                                        .map(this.exerciceMapper :: toDto)
                                        .toList() ;
    }

    @Override
    public List<ExerciceDto> FindExerciceDtoByLevelAndSubject(Long idlevel, Long idsubject) {
        Subject subject = this.subjectService.getSubjectById(idsubject);
        Level level = this.levelService.getLevelById(idlevel);
        return this.exerciceRepository.findByLevelAndSubject(level, subject)
                                        .stream()
                                        .map(this.exerciceMapper :: toDto)
                                        .toList() ;
    }

    @Override
    public Exercice FindExerciceByid(Long id) {
        return this.exerciceRepository.findById(id)
                                        .orElseThrow(() -> new NotFound_404("Exercice Not Found"));
    }

    @Override
    public ExerciceDto AddExerciceN(ExerciceNDto exerciceNDto) {
        Exercice exercice = new Exercice();
        exercice.setTitle(exerciceNDto.getTitle());
        exercice.setContent(exerciceNDto.getContent());
        exercice.setDescription(exerciceNDto.getDescription());
        exercice.setImage(exerciceNDto.getImage());
        exercice.setLevel(this.levelService.getLevelById(exerciceNDto.getLevelDtoId()));
        exercice.setSolution(this.solutionService.findSolutionById(exerciceNDto.getSolutionDtoId()));
        exercice.setSubject(this.subjectService.getSubjectById(exerciceNDto.getSubjectDtoId()));
        exercice.setUser(this.userService.getUserById(exerciceNDto.getUserDtoId()));

        Exercice exerciceSave = this.exerciceRepository.save(exercice) ;
        return this.exerciceMapper.toDto(exerciceSave) ;

    }

    @Override
    public ExerciceDto UpdateExerciceN(ExerciceNDto exerciceNDto, Long id) {
        Exercice exercice = this.exerciceRepository.findById(id).orElse(null);
        exercice.setTitle(exerciceNDto.getTitle());
        exercice.setContent(exerciceNDto.getContent());
        exercice.setDescription(exerciceNDto.getDescription());
        exercice.setImage(exerciceNDto.getImage());
        exercice.setLevel(this.levelService.getLevelById(exerciceNDto.getLevelDtoId()));
        exercice.setSolution(this.solutionService.findSolutionById(exerciceNDto.getSolutionDtoId()));
        exercice.setSubject(this.subjectService.getSubjectById(exerciceNDto.getSubjectDtoId()));
        exercice.setUser(this.userService.getUserById(exerciceNDto.getUserDtoId()));
        Exercice newExercice = this.exerciceRepository.save(exercice);
        return this.exerciceMapper.toDto(newExercice) ;

    }

}
