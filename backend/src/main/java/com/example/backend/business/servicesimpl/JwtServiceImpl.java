package com.example.backend.business.servicesimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.backend.business.services.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

    private static final String SecretKey = "6344aa5c3c28e6603cccbb5fe6d7ed47f7108636ae61031affe66ae77c348ca1";



    @Override
    public String ExtractUsername(String token) {
       return ExtractClaim(token , Claims::getSubject) ;
    }

    @Override
    public <T> T ExtractClaim(String token , Function<Claims , T> claimsresoulve){
        final Claims claims = ExtractAllClaims(token);
        return claimsresoulve.apply(claims);
    }

    @Override
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public String generateToken(Map<String , Object> extraClaims , UserDetails userDetails)
    {

        return Jwts.builder()
            .claims(extraClaims)
            .subject(userDetails.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h d'expiration
            .signWith(getSignInKey()) // Pas besoin de préciser SignatureAlgorithm
            .compact();

        /* return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 *24))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact() ; */
    }


    @Override
    public boolean isTokenValid(String token , UserDetails userDetails)
    {
        final String username = ExtractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token) ;
    }

    private boolean isTokenExpired(String token){
        return ExtractExpiration(token).before(new Date());
    }

    private Date ExtractExpiration(String token)
    {
        return ExtractClaim(token, Claims::getExpiration);
    }  



    private Claims ExtractAllClaims(String token) {
        
        return Jwts.parser()
        .verifyWith(getSignInKey()) // Utilisation de verifyWith() pour vérifier la signature
        .build()
        .parseSignedClaims(token) // parseSignedClaims() remplace parseClaimsJws()
        .getPayload();
        
        
        
        /* return Jwts
            .parser()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody(); */
    }

    private SecretKey getSignInKey()
    {
        byte[] kBytes = Decoders.BASE64.decode(SecretKey); 
        return Keys.hmacShaKeyFor(kBytes);
    }

   
    
}
