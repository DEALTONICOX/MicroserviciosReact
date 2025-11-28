package com.storefit.orders_service.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarItemRequest {

    @NotNull
    @Min(1)
    private Integer cantidad;

    @NotNull
    private String talla;
}
