package com.example.backend.business.services;

import java.util.List;

import com.example.backend.dao.entities.Category;
import com.example.backend.web.Models.dto.CategoryDto;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getCategoryDtoById(Long id);
    List<CategoryDto> getAllCategories();

    Category getCategoryById(Long id);

    void deleteCategoryById(Long id);

}
