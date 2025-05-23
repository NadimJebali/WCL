package com.example.backend.web.Models.Enum;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.example.backend.web.Models.Enum.Permission.STUDENT_CREATE;
import static com.example.backend.web.Models.Enum.Permission.STUDENT_DELETE;
import static com.example.backend.web.Models.Enum.Permission.STUDENT_READ;
import static com.example.backend.web.Models.Enum.Permission.STUDENT_UPDATE;

import static com.example.backend.web.Models.Enum.Permission.TEACHER_READ;
import static com.example.backend.web.Models.Enum.Permission.TEACHER_DELETE;
import static com.example.backend.web.Models.Enum.Permission.TEACHER_CREATE;
import static com.example.backend.web.Models.Enum.Permission.TEACHER_UPDATE;

import static com.example.backend.web.Models.Enum.Permission.ADMIN_CREATE;
import static com.example.backend.web.Models.Enum.Permission.ADMIN_DELETE;
import static com.example.backend.web.Models.Enum.Permission.ADMIN_READ;
import static com.example.backend.web.Models.Enum.Permission.ADMIN_UPDATE;



@RequiredArgsConstructor
public enum Role {

    STUDENT(
        Set.of(
            STUDENT_CREATE,
            STUDENT_DELETE,
            STUDENT_READ,
            STUDENT_UPDATE
        )
    ),
    TEACHER(
        Set.of(
            TEACHER_UPDATE,
            TEACHER_CREATE,
            TEACHER_DELETE,
            TEACHER_READ 
        )
    ),

    ADMIN(
        Set.of(
            ADMIN_READ,
            ADMIN_UPDATE,
            ADMIN_CREATE,
            ADMIN_DELETE,
       
            STUDENT_READ,
            STUDENT_UPDATE,
            STUDENT_CREATE,
            STUDENT_DELETE,
    
            TEACHER_READ,
            TEACHER_UPDATE,
            TEACHER_CREATE,
            TEACHER_DELETE
            
        )
    )
    ;

     @Getter
     private final Set<Permission> permissions;
 
     public List<SimpleGrantedAuthority> getAuthorities() {
     var authorities = getPermissions()
             .stream()
             .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
             .collect(Collectors.toList());
     authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
     return authorities;
   }
}
