package com.storefit.orders_service.Client;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import com.storefit.orders_service.Model.StockReservaItemDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CatalogClient {

        private final WebClient.Builder webClientBuilder;

        @Value("${catalog-service.url:http://localhost:8081}")
        private String catalogBaseUrl;

        public void reservarStock(List<StockReservaItemDTO> items) {
                String path = "/api/v1/productos/stock/reservar";

                WebClient client = webClientBuilder
                                .baseUrl(catalogBaseUrl)
                                .build();

                try {
                        client.post()
                                        .uri(path)
                                        .bodyValue(items)
                                        .retrieve()
                                        // Si el catálogo responde 4xx/5xx, propagamos ese mismo status y mensaje
                                        .onStatus(status -> status.isError(), r -> r.bodyToMono(String.class)
                                                        .map(msg -> new ResponseStatusException(
                                                                        r.statusCode(),
                                                                        (msg != null && !msg.isBlank())
                                                                                        ? msg
                                                                                        : "Error al reservar stock en catalog-service ("
                                                                                                        + r.statusCode()
                                                                                                        + ")")))
                                        .toBodilessEntity()
                                        .block();

                } catch (ResponseStatusException ex) {
                        // Ya viene con el código correcto (400, 404, 409, 500...)
                        throw ex;
                } catch (WebClientResponseException ex) {
                        // Errores HTTP que lanza directamente WebClient
                        throw new ResponseStatusException(
                                        ex.getStatusCode(),
                                        ex.getResponseBodyAsString(),
                                        ex);
                } catch (Exception ex) {
                        // SOLO errores de red reales (timeout, conexión rechazada, etc.)
                        throw new ResponseStatusException(
                                        HttpStatus.SERVICE_UNAVAILABLE,
                                        "No se pudo contactar al catalog-service",
                                        ex);
                }
        }
}
