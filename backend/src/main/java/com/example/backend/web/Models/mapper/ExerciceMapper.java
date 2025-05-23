package com.example.backend.web.Models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.backend.dao.entities.Exercice;
import com.example.backend.web.Models.dto.ExerciceDto;

@Mapper(componentModel = "spring")
public interface ExerciceMapper {
    
    ExerciceMapper INSTANCE = Mappers.getMapper(ExerciceMapper.class);

    @Mapping(source = "level.category" , target = "levelDto.categoryDto")
    @Mapping(source = "subject" , target = "subjectDto")  
    @Mapping(source = "subject.level" , target = "subjectDto.levelDto")
    @Mapping(source = "subject.level.category" , target = "subjectDto.levelDto.categoryDto")  
    @Mapping(source = "level" , target = "levelDto")
    @Mapping(source = "solution" , target = "solutionDto")
    @Mapping(source = "user" , target = "userDto")
    ExerciceDto toDto(Exercice exercice);

    @Mapping(source = "subjectDto" ,target = "subject")
    @Mapping(source = "subjectDto.levelDto" , target = "subject.level")
    @Mapping(source = "subjectDto.levelDto.categoryDto" , target = "subject.level.category")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "levelDto" , target = "level")
    @Mapping(source = "levelDto.categoryDto" , target = "level.category")
    @Mapping(source = "solutionDto" , target = "solution")
    @Mapping(source = "userDto" , target = "user")
    Exercice toEntity(ExerciceDto exerciceDto);
}
