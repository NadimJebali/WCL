package com.example.backend.business.services;

import java.util.List;

import com.example.backend.dao.entities.User;
import com.example.backend.web.Models.dto.UserDto;

public interface UserService {

    User getUserByEmail(String email);
    User addUser(User user);
    User getUserById(Long id);


    List<UserDto> getAllUser();
    void deleteByid(Long id);
    UserDto getUserDtoByEmail(String email);
    UserDto updateUserDto(Long id , UserDto newUserDto);
    UserDto getUserDtoById(Long id);


    


    
}
