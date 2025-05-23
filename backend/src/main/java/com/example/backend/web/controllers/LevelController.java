package com.example.backend.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.business.services.LevelService;
import com.example.backend.web.Models.dto.LevelDto;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/level")
@Tag(name = "LevelController" , description = "For ADMIN")
@PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
public class LevelController {
    
    @Autowired
    private LevelService levelService ;

    @Operation(summary = "Recover all Levels")
    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('teacher:read') or hasAuthority('student:read')")
    @GetMapping("/all")
    public ResponseEntity<List<LevelDto>> getAllLevels() {
        return new ResponseEntity<>(this.levelService.getAllLevel() , HttpStatus.OK);
    }

    @Operation(summary = "Find Level By Id")
    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('teacher:read') or hasAuthority('student:read')")
    @GetMapping("/find/{id}")
    public ResponseEntity<LevelDto> getLevelById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.levelService.getLevelDtoById(id) , HttpStatus.OK);
    }
    
    @Operation(summary = "Add Level")
    @PreAuthorize("hasAuthority('admin:create')")
    @PostMapping("/add")
    public ResponseEntity<LevelDto> addLevel(@RequestBody LevelDto levelDto) {
        return new ResponseEntity<>(this.levelService.addLevel(levelDto) , HttpStatus.CREATED);
    }

    @Operation(summary = "Delete Level")
    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/deletelevel/{id}")
    public ResponseEntity<Void> deletelevel(@PathVariable("id")Long id)
    {
        this.levelService.deleteLevelById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
