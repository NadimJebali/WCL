package com.example.backend.web.Models.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    
     ADMIN_READ("admin:read"),
     ADMIN_UPDATE("admin:update"),
     ADMIN_CREATE("admin:create"),
     ADMIN_DELETE("admin:delete"),

     STUDENT_READ("student:read"),
     STUDENT_UPDATE("student:update"),
     STUDENT_CREATE("student:create"),
     STUDENT_DELETE("student:delete"),
     
     TEACHER_READ("teacher:read"),
     TEACHER_UPDATE("teacher:update"),
     TEACHER_CREATE("teacher:create"),
     TEACHER_DELETE("teacher:delete")
     ;

     @Getter
     private final String permission;
}
