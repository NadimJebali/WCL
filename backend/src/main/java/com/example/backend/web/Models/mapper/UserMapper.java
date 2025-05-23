package com.example.backend.web.Models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.backend.dao.entities.User;
import com.example.backend.web.Models.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDto userDto);

    UserDto toDto(User user) ;
    
}
