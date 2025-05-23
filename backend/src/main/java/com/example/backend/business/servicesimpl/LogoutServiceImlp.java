package com.example.backend.business.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.example.backend.business.services.TokenService;
import com.example.backend.dao.repositories.TokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class LogoutServiceImlp implements LogoutHandler {

    @Autowired
    private TokenService tokenService ;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        
        final String authHeader = request.getHeader("Authorization");
        final String jwt ;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return ;
        }
        jwt = authHeader.substring(7);
        var storedToken = this.tokenRepository.findByTokenname(jwt).orElse(null);
        if(storedToken != null)
        {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            this.tokenService.addtoken(storedToken);
        }
    }

   
    
}
