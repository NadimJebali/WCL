package com.example.backend.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.business.services.GeminiService;
import com.example.backend.web.Models.dto.QuestionDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/gemini")
@Tag(name = "GeminiController" , description = "For All")
public class GeminiController {

    @Autowired
    private GeminiService geminiService ;

    @Operation(summary = "Chat with Gemini")
    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody QuestionDto payload){
        String question = payload.getQuery();
        String answer = geminiService.getAnswer(question);
        return new ResponseEntity<>(answer , HttpStatus.OK);
    }
    
}
