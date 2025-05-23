package com.example.backend.business.services;

import java.util.List;

import com.example.backend.dao.entities.Subject;
import com.example.backend.web.Models.dto.SubjectDto;

public interface SubjectService {

    SubjectDto addSubjectDto (SubjectDto subjectDto);
    SubjectDto getSubjectDtoById(Long id);
    Subject getSubjectById(Long id) ;
    List<SubjectDto> getAllSubject();

    void deleteSubjectById (Long id);


    
}
