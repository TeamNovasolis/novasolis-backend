package com.teamnovasolis.novasolis.security;

import com.teamnovasolis.novasolis.NovasolisConfigProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenService {

    private NovasolisConfigProperties novasolisConfigProperties;

    @Autowired
    public JwtTokenService(NovasolisConfigProperties novasolisConfigProperties) {
        this.novasolisConfigProperties = novasolisConfigProperties;
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + novasolisConfigProperties.jwtExpire());

        return Jwts.builder()
                .setSubject(username)
                .setIssuer("TeamNovaSolis")
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, novasolisConfigProperties.jwtSecret())
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(novasolisConfigProperties.jwtSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(novasolisConfigProperties.jwtSecret()).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }
}