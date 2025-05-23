package com.example.backend.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.business.services.SolutionService;
import com.example.backend.web.Models.dto.SolutionDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/solution")
@PreAuthorize("hasRole('TEACHER')")
@Tag(name = "SolutionController" , description = "For Teacher")
public class SolutionController {

    @Autowired
    private SolutionService solutionService ;

    @Operation(summary = "Recover SolutionByid (teacher only)")
    @PreAuthorize("hasAuthority('teacher:read')")
    @GetMapping("/find/{id}")
    public ResponseEntity<SolutionDto> FindSolutionDtoById(@PathVariable Long id) {
        return new ResponseEntity<>(this.solutionService.findSolutionDtoById(id) , HttpStatus.OK);
    }
    

    @Operation(summary = "Add Solution (teacher only)")
    @PreAuthorize("hasAuthority('teacher:create')")
    @PostMapping("/add")
    public ResponseEntity<SolutionDto> AddSolutionDto(@RequestBody SolutionDto solutionDto) {
        return new ResponseEntity<>(this.solutionService.addSolutionDto(solutionDto) , HttpStatus.CREATED);
    }

    
    
}
