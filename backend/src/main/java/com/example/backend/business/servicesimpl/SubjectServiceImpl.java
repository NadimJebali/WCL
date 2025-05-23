package com.example.backend.business.servicesimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.business.services.SubjectService;
import com.example.backend.dao.entities.Subject;
import com.example.backend.dao.repositories.SubjectRepository;
import com.example.backend.exception.modelsexception.NotFound_404;
import com.example.backend.web.Models.dto.SubjectDto;
import com.example.backend.web.Models.mapper.SubjectMapper;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository ;

    @Autowired
    private SubjectMapper subjectMapper ;

    @Override
    public SubjectDto addSubjectDto(SubjectDto subjectDto) {
        Subject subject = this.subjectRepository.save(this.subjectMapper.toEntity(subjectDto));
        return this.subjectMapper.toDto(subject) ;
    }

    @Override
    public SubjectDto getSubjectDtoById(Long id) {
        return this.subjectRepository.findById(id)
                                        .map(this.subjectMapper :: toDto)
                                        .orElseThrow(() -> new NotFound_404("Subject Not Found"));       
    }

    @Override
    public List<SubjectDto> getAllSubject() {
        return this.subjectRepository.findAll()
                                        .stream()
                                        .map(this.subjectMapper :: toDto)
                                        .toList();
    }

    @Override
    public Subject getSubjectById(Long id) {
        return this.subjectRepository.findById(id).orElseThrow(()-> new NotFound_404("Subject Not Found")) ;
    }

    @Override
    public void deleteSubjectById(Long id) {
        Subject subject = getSubjectById(id);
        this.subjectRepository.delete(subject);

    }
    
}
