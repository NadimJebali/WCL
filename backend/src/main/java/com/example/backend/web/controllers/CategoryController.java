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

import com.example.backend.business.services.CategoryService;
import com.example.backend.web.Models.dto.CategoryDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/category")
@Tag(name = "CategoryController" , description = "ADMIN")
@PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService ;

    @Operation(summary = "Recover all Categories")
    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('teacher:read') or hasAuthority('student:read')")
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(this.categoryService.getAllCategories() , HttpStatus.OK);
    }

    @Operation(summary = "Find Category By Id")
    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('teacher:read') or hasAuthority('student:read')")
    @GetMapping("/find/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.categoryService.getCategoryDtoById(id) , HttpStatus.OK);
    }
    
    @Operation(summary = "Add Category")
    @PreAuthorize("hasAuthority('admin:create')")
    @PostMapping("/add")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(this.categoryService.addCategory(categoryDto) , HttpStatus.CREATED);
    }

    @Operation(summary = "Delete Category")
    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/deletecategory/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id")Long id)
    {
        this.categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
}
