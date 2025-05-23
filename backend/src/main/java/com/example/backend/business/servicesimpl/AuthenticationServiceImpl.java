package com.example.backend.business.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.business.services.AuthenticationService;
import com.example.backend.business.services.JwtService;
import com.example.backend.business.services.TokenService;
import com.example.backend.business.services.UserService;
import com.example.backend.dao.entities.Token;
import com.example.backend.dao.entities.User;
import com.example.backend.web.Models.AuthenticationReponse;
import com.example.backend.web.Models.AuthenticationRequest;
import com.example.backend.web.Models.RegisterRequest;
import com.example.backend.web.Models.Enum.TypeToken;

@Service
public class AuthenticationServiceImpl implements AuthenticationService  
{


    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService ;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Autowired
    private JwtService jwtService ;

    @Autowired
    private AuthenticationManager authenticationManager ;



    @Override
    public AuthenticationReponse register(RegisterRequest registerRequest) {
        User user =  User.builder()
                        .firstname(registerRequest.getFirstname())
                        .lastname(registerRequest.getLastname())
                        .email(registerRequest.getEmail())
                        .password(this.passwordEncoder.encode(registerRequest.getPassword()))
                        .role(registerRequest.getRole())
                        .build() ;
        User u = this.userService.addUser(user);
        var jwt = this.jwtService.generateToken(u);
        Token token = Token.builder()
                            .tokenname(jwt)
                            .typetoken(TypeToken.BEARER)
                            .expired(false)
                            .revoked(false)
                            .user(u)
                            .build();
        this.tokenService.addtoken(token);
        AuthenticationReponse auth = new AuthenticationReponse(jwt);
        return auth ;
    }

    @Override
    public AuthenticationReponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );

        User user = this.userService.getUserByEmail(authenticationRequest.getEmail());
        var jwt = this.jwtService.generateToken(user);
        Token token = Token.builder()
                            .tokenname(jwt)
                            .typetoken(TypeToken.BEARER)
                            .expired(false)
                            .revoked(false)
                            .user(user)
                            .build();
        revokeAllUser(user);
        this.tokenService.addtoken(token);
        AuthenticationReponse auth = new AuthenticationReponse(jwt);
        return auth ;
        
    }


    private void revokeAllUser(User user)
    {
        var tokens = this.tokenService.getAllValidTokenByUserId(user.getId());
        if(tokens == null)
            return ;
        tokens.forEach(t->{
            t.setExpired(true);
            t.setRevoked(true);
        });
    }

    
}
