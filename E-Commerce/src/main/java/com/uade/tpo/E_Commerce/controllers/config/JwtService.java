package com.uade.tpo.E_Commerce.controllers.config;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class JwtService {
    @Value("${application.security.jwt.secretKey}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    //metodo para generar el token
    public String generateToken(
            UserDetails userDetails) {
        return buildToken(userDetails, jwtExpiration);
    }

    //esto arma el token
    private String buildToken(
            UserDetails userDetails,
            long expiration) {
        UserPrincipal user = (UserPrincipal) userDetails;
        return Jwts
                .builder()
                .subject(userDetails.getUsername()) // aca se fija el mail del usuario
                .issuedAt(new Date(System.currentTimeMillis()))//aca se pone el horario de creacion
                .claim("role", user.getRole()) // guardás el rol
                .expiration(new Date(System.currentTimeMillis() + expiration))// aca se pone cuando se vence
                .signWith(getSecretKey())// se fija la secret key
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractClaim(token, Claims::getSubject);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        SecretKey secretKeySpec = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return secretKeySpec;
    }
}
