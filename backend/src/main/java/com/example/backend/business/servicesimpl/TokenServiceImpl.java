package com.example.backend.business.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.business.services.TokenService;
import com.example.backend.dao.entities.Token;
import com.example.backend.dao.repositories.TokenRepository;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository ;

    @Override
    public Token addtoken(Token token) {
        return this.tokenRepository.save(token);
    }

    @Override
    public List<Token> getAllValidTokenByUserId(Long id) {
        return this.tokenRepository.findAllValidTokenByUser(id);
    }

    @Override
    public Token getByTokenname(String token) {
        return this.getByTokenname(token);
    }
    
}
