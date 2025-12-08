package com.storefit.orders_service.Controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

/**
 * Devuelve un cuerpo JSON con el mensaje cuando se lanza una ResponseStatusException.
 * Esto evita que el frontend reciba solo el status 404/400 sin detalle.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handle(ResponseStatusException ex) {
        String msg = ex.getReason();
        if (msg == null || msg.isBlank()) {
            msg = ex.getMessage();
        }
        return ResponseEntity.status(ex.getStatusCode())
                .body(Map.of("message", msg));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", "Error interno en orders-service"));
    }
}
