package com.storefit.orders_service.Controller;

import com.storefit.orders_service.Model.AgregarItemRequest;
import com.storefit.orders_service.Model.ActualizarItemRequest;
import com.storefit.orders_service.Model.CarritoDTO;
import com.storefit.orders_service.Service.CarritoService;
import com.storefit.orders_service.security.Authorization;
import com.storefit.orders_service.security.RequestUser;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5174", allowedHeaders = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE,
        RequestMethod.OPTIONS
})
@RestController
@RequestMapping("/api/v1/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @RequestMapping(method = RequestMethod.OPTIONS, path = "/**")
    public ResponseEntity<Void> corsPreflight() {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<CarritoDTO> obtenerCarrito(
            @RequestHeader("X-User-Rut") String headerRut,
            @RequestHeader("X-User-Rol") String headerRol) {

        RequestUser user = Authorization.fromHeaders(headerRut, headerRol);
        CarritoDTO carrito = carritoService.obtenerCarritoDeUsuario(user.getRut());
        return ResponseEntity.ok(carrito);
    }

    @PostMapping("/items")
    public ResponseEntity<CarritoDTO> agregarItem(
            @RequestHeader("X-User-Rut") String headerRut,
            @RequestHeader("X-User-Rol") String headerRol,
            @Valid @RequestBody AgregarItemRequest request) {

        RequestUser user = Authorization.fromHeaders(headerRut, headerRol);
        CarritoDTO carrito = carritoService.agregarItem(user.getRut(), request);
        return ResponseEntity.ok(carrito);
    }

    // ⬇⬇⬇ CAMBIADO: productoId por query param
    @PutMapping("/items")
    public ResponseEntity<CarritoDTO> actualizarItem(
            @RequestParam("productoId") String productoId,
            @RequestHeader("X-User-Rut") String headerRut,
            @RequestHeader("X-User-Rol") String headerRol,
            @Valid @RequestBody ActualizarItemRequest request) {

        RequestUser user = Authorization.fromHeaders(headerRut, headerRol);
        CarritoDTO carrito = carritoService.actualizarCantidad(user.getRut(), productoId, request);
        return ResponseEntity.ok(carrito);
    }

    // ⬇⬇⬇ CAMBIADO: productoId y talla por query param
    @DeleteMapping("/items")
    public ResponseEntity<CarritoDTO> eliminarItem(
            @RequestParam("productoId") String productoId,
            @RequestParam("talla") String talla,
            @RequestHeader("X-User-Rut") String headerRut,
            @RequestHeader("X-User-Rol") String headerRol) {

        RequestUser user = Authorization.fromHeaders(headerRut, headerRol);
        CarritoDTO carrito = carritoService.eliminarItem(user.getRut(), productoId, talla);
        return ResponseEntity.ok(carrito);
    }

    @DeleteMapping
    public ResponseEntity<Void> limpiarCarrito(
            @RequestHeader("X-User-Rut") String headerRut,
            @RequestHeader("X-User-Rol") String headerRol) {

        RequestUser user = Authorization.fromHeaders(headerRut, headerRol);
        carritoService.limpiarCarrito(user.getRut());
        return ResponseEntity.noContent().build();
    }
}
