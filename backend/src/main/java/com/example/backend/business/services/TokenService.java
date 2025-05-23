package com.example.backend.business.services;

import java.util.List;

import com.example.backend.dao.entities.Token;

public interface TokenService {
    Token addtoken(Token token);
    List<Token> getAllValidTokenByUserId(Long id);
    Token getByTokenname(String token) ;
}
