package com.example.backend.web.Models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.backend.dao.entities.Solution;
import com.example.backend.web.Models.dto.SolutionDto;

@Mapper(componentModel = "spring")
public interface SolutionMapper {

    SolutionMapper INSTANCE = Mappers.getMapper(SolutionMapper.class);

    //@Mapping(source = "exercice" , target = "exerciceDto")
    SolutionDto toDto(Solution solution);

    @Mapping(target = "id" , ignore = true)
    //@Mapping(source = "exerciceDto" , target = "exercice")
    Solution toEntity(SolutionDto solutionDto);
    
}
