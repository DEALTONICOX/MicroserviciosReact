package com.storefit.orders_service.Model;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgregarItemRequest {

    // Puede venir como:
    // - "3/10"
    // - "10"
    // - { "id": { "idCategoria": 3, "idProducto": 10 } }
    @NotNull
    private JsonNode productoId;

    @NotNull
    @Min(1)
    private Integer cantidad;
    private String nombre;

    private Integer precio;   

    @NotNull
    private String talla;
}
