package com.example.backend.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.dao.entities.Exercice;
import com.example.backend.dao.entities.Level;
import com.example.backend.dao.entities.Subject;

import java.util.List;


@Repository
public interface ExerciceRepository extends JpaRepository<Exercice , Long> {

    List<Exercice> findByLevel(Level level);
    List<Exercice> findBySubject(Subject subject);
    List<Exercice> findByLevelAndSubject(Level level , Subject subject);

    
}
