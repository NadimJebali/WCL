package com.example.backend.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.dao.entities.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject , Long> {
    
}
