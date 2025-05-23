package com.example.backend.web.Models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.backend.dao.entities.Category;
import com.example.backend.web.Models.dto.CategoryDto;



@Mapper(componentModel="spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    
    CategoryDto toDto(Category category);
    
    @Mapping(target = "id" , ignore = true)
    Category toEntity(CategoryDto categoryDto);
}
