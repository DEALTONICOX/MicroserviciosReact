package com.storefit.users_service.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getRequestURI();

        //1) NUNCA bloquear nada de /api/v1/registros/**
        // (login, registro-completo, cambiar-contrasenia, etc.)
        if (path.startsWith("/api/v1/registros")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                if (jwtUtil.isTokenValid(token)) {
                    Claims claims = jwtUtil.extractAllClaims(token);
                    String correo = claims.getSubject();
                    String rol = (String) claims.get("rol"); // "ADMIN", "CLIENTE", etc.

                    var authorities = List.of(
                            // Spring espera ROLE_ADMIN, ROLE_CLIENTE, etc.
                            new SimpleGrantedAuthority("ROLE_" + rol)
                    );

                    var auth = new UsernamePasswordAuthenticationToken(
                            correo,
                            null,
                            authorities
                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    // Token inválido/expirado -> no autenticamos a nadie
                    SecurityContextHolder.clearContext();
                }
            } catch (Exception ex) {
                // ⚠️ IMPORTANTE: NO devolvemos 403 aquí, solo limpiamos
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
}