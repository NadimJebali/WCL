package com.example.backend.web.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.backend.business.services.ExerciceService;
import com.example.backend.web.Models.dto.ExerciceDto;
import com.example.backend.web.Models.dto.ExerciceNDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;






@RestController()
@RequestMapping("/exercice")
@PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
@Tag(name = "ExerciceController" , description = "For Student and Teacher")
public class ExerciceController {


    @Autowired
    private ExerciceService exerciceService;
    
    @Operation(summary = "Recover all Exercices (student and teacher only)")
    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('teacher:read') or hasAuthority('student:read')")
    @GetMapping("/all")
    public ResponseEntity<List<ExerciceDto>> DisplayAll() {
        return  new ResponseEntity<>(this.exerciceService.AllExercices() , HttpStatus.OK) ;
    }


    @Operation(summary = "Find Exercice By Id (student and teacher only)")
    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('teacher:read') or hasAuthority('student:read')")
    @GetMapping("/find/{id}")
    public ResponseEntity<ExerciceDto> FindExercice(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.exerciceService.FindExerciceDtoByid(id) , HttpStatus.OK);
    }

    @Operation(summary = "Find Exercice By Level Id (student and teacher only)")
    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('teacher:read') or hasAuthority('student:read')")
    @GetMapping("/find/bylevel/{id}")
    public ResponseEntity<List<ExerciceDto>> getExerciceDtobylevel(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.exerciceService.FindExerciceDtoByLevel(id) , HttpStatus.OK);
    }

    @Operation(summary = "Find Exercice By Subject Id (student and teacher only)")
    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('teacher:read') or hasAuthority('student:read')")
    @GetMapping("/find/bysubject/{id}")
    public ResponseEntity<List<ExerciceDto>> getExerciceDtobysubject(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.exerciceService.FindExerciceDtoBySubject(id) , HttpStatus.OK);
    }

    @Operation(summary = "Find Exercice By Level Id and Subject Id (student and teacher only)")
    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('teacher:read') or hasAuthority('student:read')")
    @GetMapping("/find/bylevelandsubject/{idlevel}/{idsubject}")
    public ResponseEntity<List<ExerciceDto>> getExerciceDtobylevelsubject(@PathVariable("idlevel") Long idlevel , @PathVariable("idsubject") Long idsubject) {
        return new ResponseEntity<>(this.exerciceService.FindExerciceDtoByLevelAndSubject(idlevel , idsubject) , HttpStatus.OK);
    }

    @Operation(summary = "Add Exercice (teacher only)")
    @PreAuthorize("hasAuthority('admin:create') or hasAuthority('teacher:create')")
    @PostMapping("/add")
    public ResponseEntity<ExerciceDto> AddExercice(@RequestBody ExerciceDto exerciceDTO) {
        return new ResponseEntity<>(this.exerciceService.AddExercice(exerciceDTO) , HttpStatus.CREATED);
    }

    @Operation(summary = "Add Exercice New Input (teacher only)")
    @PreAuthorize("hasAuthority('admin:create') or hasAuthority('teacher:create')")
    @PostMapping("/addnew")
    public ResponseEntity<ExerciceDto> AddExercicenew(@RequestBody ExerciceNDto exerciceNDTO) {
        return new ResponseEntity<>(this.exerciceService.AddExerciceN(exerciceNDTO) , HttpStatus.CREATED);
    }

    @Operation(summary = "Update Exercice (title , description , content , image) (teacher only)")
    @PreAuthorize("hasAuthority('admin:update') or hasAuthority('teacher:update')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ExerciceDto> updateExercice(@RequestBody ExerciceDto exerciceDTO , @PathVariable("id") Long id) {
        return new ResponseEntity<>(this.exerciceService.UpdateExercice(exerciceDTO, id) , HttpStatus.OK);
    }

    @Operation(summary = "Update Exercice (title , description , content , image) (teacher only)")
    @PreAuthorize("hasAuthority('admin:update') or hasAuthority('teacher:update')")
    @PutMapping("/updatenew/{id}")
    public ResponseEntity<ExerciceDto> updateExercicenew(@RequestBody ExerciceNDto exerciceDTO , @PathVariable("id") Long id) {
        return new ResponseEntity<>(this.exerciceService.UpdateExerciceN(exerciceDTO, id) , HttpStatus.OK);
    }

    @Operation(summary = "Delete Exercice (teacher only)")
    @PreAuthorize("hasAuthority('admin:delete') or hasAuthority('teacher:delete')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> DeleteExercice(@PathVariable("id") Long id){
        this.exerciceService.DeleteExercice(id) ;
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }

    
    
    
    
    
    
}
