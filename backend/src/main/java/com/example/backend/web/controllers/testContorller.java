package com.example.backend.web.controllers;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/test")
public class testContorller {

    

    @Tag(name = "Get" , description = "affiche salut")
    @GetMapping("/")
    public String test() {
        return "salut";
    }
    
}
