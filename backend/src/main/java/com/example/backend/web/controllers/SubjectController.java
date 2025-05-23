package com.example.backend.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.business.services.SubjectService;
import com.example.backend.web.Models.dto.SubjectDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/subject")
@Tag(name = "SubjectController" , description = "For ADMIN")
@PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
public class SubjectController {
    
    @Autowired
    private SubjectService subjectService ;


    @Operation(summary = "Recover all Subjects")
    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('teacher:read') or hasAuthority('student:read')")
    @GetMapping("/all")
    public ResponseEntity<List<SubjectDto>> getAllSubjects() {
        return new ResponseEntity<>(this.subjectService.getAllSubject() , HttpStatus.OK);
    }

    @Operation(summary = "Find Subject By Id")
    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('teacher:read') or hasAuthority('student:read')")
    @GetMapping("/find/{id}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.subjectService.getSubjectDtoById(id) , HttpStatus.OK);
    }
    
    @Operation(summary = "Add Subject")
    @PreAuthorize("hasAuthority('admin:create')")
    @PostMapping("/add")
    public ResponseEntity<SubjectDto> addSubject(@RequestBody SubjectDto subjectDto) {
        return new ResponseEntity<>(this.subjectService.addSubjectDto(subjectDto) , HttpStatus.CREATED);
    }
    
    @Operation(summary = "Delete Subject")
    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/deletesubject/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable("id")Long id)
    {
        this.subjectService.deleteSubjectById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
