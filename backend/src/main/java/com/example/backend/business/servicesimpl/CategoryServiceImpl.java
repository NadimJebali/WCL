package com.example.backend.business.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.business.services.CategoryService;
import com.example.backend.dao.entities.Category;
import com.example.backend.dao.repositories.CategoryRepository;
import com.example.backend.exception.modelsexception.NotFound_404;
import com.example.backend.web.Models.dto.CategoryDto;
import com.example.backend.web.Models.mapper.CategoryMapper;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository ;

    @Autowired
    private CategoryMapper categoryMapper ;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = this.categoryRepository.save(this.categoryMapper.toEntity(categoryDto));
        return this.categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto getCategoryDtoById(Long id) {
        return this.categoryRepository.findById(id)
                                        .map(this.categoryMapper :: toDto)
                                        .orElseThrow(() -> new NotFound_404("Category Not Found !"));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return this.categoryRepository.findAll()
                                        .stream()
                                        .map(this.categoryMapper :: toDto)
                                        .toList();
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category category = getCategoryById(id);
        this.categoryRepository.delete(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return this.categoryRepository.findById(id)
                                        .orElseThrow(() -> new NotFound_404("Category Not Found") );

    }
    
    
}
