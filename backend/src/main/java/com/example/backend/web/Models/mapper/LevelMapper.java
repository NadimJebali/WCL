package com.example.backend.web.Models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.backend.dao.entities.Level;
import com.example.backend.web.Models.dto.LevelDto;

@Mapper(componentModel = "spring")
public interface LevelMapper {

    LevelMapper INSTANCE = Mappers.getMapper(LevelMapper.class);

    @Mapping(source = "category" , target = "categoryDto")
    LevelDto toDto(Level level);

    @Mapping(target = "id" , ignore = true)
    @Mapping(source = "categoryDto" , target = "category")
    Level toEntity(LevelDto levelDto);
}
