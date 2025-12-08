package com.storefit.users_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // API REST: sin CSRF y sin sesiones de servidor
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
                // ✅ TODO lo de /api/v1/registros/** es público (login / registro / cambiar pass)
                .requestMatchers(
                    "/api/v1/registros/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()

                // Endpoints de usuarios se validan por headers en los controllers
                // (Authorization.fromHeaders + requireOwnerOrAdmin), asÃ­ que no exigimos rol aquÃ­
                .requestMatchers("/api/v1/usuarios/**").permitAll()

                //El resto requiere estar autenticado (cualquier rol)
                .anyRequest().authenticated()
            )
            // Filtro JWT antes del filtro de login por formulario
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
