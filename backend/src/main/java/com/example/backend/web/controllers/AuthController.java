package com.example.backend.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.business.services.AuthenticationService;

import com.example.backend.web.Models.AuthenticationReponse;
import com.example.backend.web.Models.AuthenticationRequest;
import com.example.backend.web.Models.RegisterRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/auth")
@Tag(name = "AuthenticationController" , description = "For Admin ,Student , Teacher")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService ;

 

    @Operation(summary = "Register User")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationReponse> register(@RequestBody RegisterRequest registerRequest ) {
        return new ResponseEntity<AuthenticationReponse>(this.authenticationService.register(registerRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Authntication User")
    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationReponse> auth(@RequestBody AuthenticationRequest authenticationRequest) {
        return new ResponseEntity<AuthenticationReponse>(this.authenticationService.authenticate(authenticationRequest), HttpStatus.OK);
    }

    @Operation(summary = "Permet de déconnecter l'utilisateur en invalidant son token JWT.")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token) {
        // Code du logout comme ci-dessus
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
