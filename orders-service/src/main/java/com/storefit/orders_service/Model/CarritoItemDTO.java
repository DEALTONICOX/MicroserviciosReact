package com.storefit.orders_service.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoItemDTO {

    // Coincide con el productoId que maneja el frontend (string, a veces "categoria/id")
    private String productoId;

    private String nombre;   // Por ahora lo rellenamos simple, puedes luego traerlo desde catalog-service
    private Integer precio;  // Precio unitario
    private Integer cantidad;
    private String talla;
    private String imagen;   // URL de imagen (placeholder si no hay)
}
