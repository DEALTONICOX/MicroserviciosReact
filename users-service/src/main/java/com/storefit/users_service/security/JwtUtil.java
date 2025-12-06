package com.storefit.users_service.security;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // OJO: en prod esto debería ir en application.properties y ser MUCHO más largo
    private static final String SECRET = "CLAVE_SUPER_SECRETA_PARA_STOREFIT_WEB_BASTANTE_LARGA_1234567890";

    // 2 horas de duración
    private static final long EXPIRATION_MILLIS = 1000L * 60 * 60 * 2;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String correo, String rut, String rolNombre) {
        Date ahora = new Date();
        Date expira = new Date(ahora.getTime() + EXPIRATION_MILLIS);

        return Jwts.builder()
                .setSubject(correo) // username principal
                .addClaims(Map.of(
                        "rut", rut,
                        "rol", rolNombre
                ))
                .setIssuedAt(ahora)
                .setExpiration(expira)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractCorreo(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractRut(String token) {
        Object v = extractAllClaims(token).get("rut");
        return v != null ? v.toString() : null;
    }

    public String extractRol(String token) {
        Object v = extractAllClaims(token).get("rol");
        return v != null ? v.toString() : null;
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public boolean isTokenValid(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}
