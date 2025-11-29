package com.storefit.orders_service.Service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.JsonNode;
import com.storefit.orders_service.Model.ActualizarItemRequest;
import com.storefit.orders_service.Model.AgregarItemRequest;
import com.storefit.orders_service.Model.CarritoDTO;
import com.storefit.orders_service.Model.CarritoItemDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarritoService {

    // key = rut del usuario
    private final Map<String, CarritoDTO> carritosPorUsuario = new ConcurrentHashMap<>();

    /**
     * Obtiene el carrito del usuario. Si no existe, devuelve uno vacío.
     */
    public CarritoDTO obtenerCarritoDeUsuario(String rutUsuario) {
        return carritosPorUsuario.computeIfAbsent(rutUsuario, rut -> {
            Instant ahora = Instant.now();
            return CarritoDTO.builder()
                    .id("carrito-" + rut)
                    .usuarioId(rut)
                    .total(0)
                    .createdAt(ahora)
                    .updatedAt(ahora)
                    .build();
        });
    }

    /**
     * Agrega un ítem al carrito del usuario (o suma cantidad si ya existe misma
     * talla).
     */
    public CarritoDTO agregarItem(String rutUsuario, AgregarItemRequest request) {
        String productoId = extraerProductoId(request.getProductoId());
        if (productoId == null || productoId.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "productoId invalido");
        }

        if (request.getCantidad() == null || request.getCantidad() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad debe ser mayor a 0");
        }

        if (request.getTalla() == null || request.getTalla().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe indicar una talla");
        }

        CarritoDTO carrito = obtenerCarritoDeUsuario(rutUsuario);

        // Buscar si ya existe un item con mismo productoId y talla
        CarritoItemDTO existente = carrito.getItems().stream()
                .filter(i -> productoId.equals(i.getProductoId())
                        && request.getTalla().equalsIgnoreCase(i.getTalla()))
                .findFirst()
                .orElse(null);

        if (existente != null) {
            existente.setCantidad(existente.getCantidad() + request.getCantidad());
        } else {

            CarritoItemDTO nuevo = CarritoItemDTO.builder()
                    .productoId(productoId)
                    .nombre(request.getNombre() != null ? request.getNombre() : "Producto " + productoId)
                    .precio(request.getPrecio() != null ? request.getPrecio() : 0)
                    .cantidad(request.getCantidad())
                    .talla(request.getTalla())
                    .imagen(null)
                    .build();
            carrito.getItems().add(nuevo);
        }

        recalcularTotal(carrito);
        return carrito;
    }

    /**
     * Actualiza la cantidad de un item existente.
     */
    public CarritoDTO actualizarCantidad(String rutUsuario, String productoIdPath, ActualizarItemRequest request) {
        if (request.getCantidad() == null || request.getCantidad() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad debe ser mayor a 0");
        }
        if (request.getTalla() == null || request.getTalla().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe indicar una talla");
        }

        CarritoDTO carrito = obtenerCarritoDeUsuario(rutUsuario);
        CarritoItemDTO item = carrito.getItems().stream()
                .filter(i -> productoIdPath.equals(i.getProductoId())
                        && request.getTalla().equalsIgnoreCase(i.getTalla()))
                .findFirst()
                .orElse(null);

        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item no encontrado en el carrito");
        }

        item.setCantidad(request.getCantidad());
        recalcularTotal(carrito);
        return carrito;
    }

    /**
     * Elimina un item del carrito según productoId + talla.
     */
    public CarritoDTO eliminarItem(String rutUsuario, String productoIdPath, String talla) {
        if (talla == null || talla.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe indicar una talla");
        }

        CarritoDTO carrito = obtenerCarritoDeUsuario(rutUsuario);
        boolean removed = carrito.getItems().removeIf(i -> productoIdPath.equals(i.getProductoId())
                && talla.equalsIgnoreCase(i.getTalla()));

        if (!removed) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item no encontrado en el carrito");
        }

        recalcularTotal(carrito);
        return carrito;
    }

    /**
     * Vacía el carrito del usuario.
     */
    public void limpiarCarrito(String rutUsuario) {
        CarritoDTO carrito = obtenerCarritoDeUsuario(rutUsuario);
        carrito.getItems().clear();
        recalcularTotal(carrito);
    }

    // ================== Helpers internos ==================

    private void recalcularTotal(CarritoDTO carrito) {
        int total = carrito.getItems().stream()
                .mapToInt(i -> (i.getPrecio() != null ? i.getPrecio() : 0)
                        * (i.getCantidad() != null ? i.getCantidad() : 0))
                .sum();
        carrito.setTotal(total);
        carrito.setUpdatedAt(Instant.now());
        if (carrito.getCreatedAt() == null) {
            carrito.setCreatedAt(carrito.getUpdatedAt());
        }
    }

    /**
     * Soporta varios formatos de productoId (string u objeto).
     */
    private String extraerProductoId(JsonNode node) {
        if (node == null || node.isNull()) {
            return null;
        }

        // Caso: texto simple (ej: "10" o "3/10")
        if (node.isTextual() || node.isNumber()) {
            return node.asText();
        }

        // Caso: { id: { idCategoria, idProducto } }
        if (node.has("id")) {
            JsonNode idNode = node.get("id");
            if (idNode != null) {
                // { "id": 10 }
                if (idNode.isTextual() || idNode.isNumber()) {
                    return idNode.asText();
                }
                // { "id": { "idCategoria": 3, "idProducto": 10 } }
                if (idNode.has("idCategoria") && idNode.has("idProducto")) {
                    String cat = idNode.get("idCategoria").asText();
                    String prod = idNode.get("idProducto").asText();
                    return cat + "/" + prod;
                }
            }
        }

        // Caso: { "idCategoria": 3, "idProducto": 10 }
        if (node.has("idCategoria") && node.has("idProducto")) {
            String cat = node.get("idCategoria").asText();
            String prod = node.get("idProducto").asText();
            return cat + "/" + prod;
        }

        // Si no reconocemos el formato:
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Formato de productoId no soportado");
    }
}
