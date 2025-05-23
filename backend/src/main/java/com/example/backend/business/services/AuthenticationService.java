package com.example.backend.business.services;

import com.example.backend.web.Models.AuthenticationReponse;
import com.example.backend.web.Models.AuthenticationRequest;
import com.example.backend.web.Models.RegisterRequest;

public interface AuthenticationService {
    
    AuthenticationReponse register(RegisterRequest registerRequest) ;
    AuthenticationReponse authenticate(AuthenticationRequest authenticationRequest);
}
