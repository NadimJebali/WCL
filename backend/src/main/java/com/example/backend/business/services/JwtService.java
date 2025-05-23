package com.example.backend.business.services;

import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {
    
    String ExtractUsername(String token) ;
    <T> T ExtractClaim(String token , Function<Claims , T> claimsresoulve);
    String generateToken(Map<String , Object> extraClaims , UserDetails userDetails);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token , UserDetails userDetails);
}
