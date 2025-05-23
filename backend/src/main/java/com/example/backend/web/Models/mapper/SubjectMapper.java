package com.example.backend.web.Models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.backend.dao.entities.Subject;
import com.example.backend.web.Models.dto.SubjectDto;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    @Mapping(source = "level" , target = "levelDto")
    @Mapping(source = "level.category" , target = "levelDto.categoryDto")
    SubjectDto toDto(Subject subject);
    
    @Mapping(target="id" , ignore=true)
    @Mapping(source = "levelDto" , target = "level")
    @Mapping(source = "levelDto.categoryDto" , target = "level.category")
    Subject toEntity(SubjectDto subjectDto);
}
