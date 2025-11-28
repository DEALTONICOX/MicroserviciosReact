package com.storefit.orders_service.Model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoDTO {

    private String id;          // Puedes usar "carrito-{rut}" o UUID
    private String usuarioId;   // RUT del usuario

    @Builder.Default
    private List<CarritoItemDTO> items = new ArrayList<>();

    private Integer total;      // Suma de (precio * cantidad)

    private Instant createdAt;
    private Instant updatedAt;
}
