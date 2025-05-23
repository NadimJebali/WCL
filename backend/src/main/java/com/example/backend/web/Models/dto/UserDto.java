package com.example.backend.web.Models.dto;

import com.example.backend.web.Models.Enum.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id ; 
    private String firstname ;
    private String lastname ;
    private String email ;
    private Role role ;
}
