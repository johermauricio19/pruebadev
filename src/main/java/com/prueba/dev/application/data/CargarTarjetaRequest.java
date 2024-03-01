package com.prueba.dev.application.data;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CargarTarjetaRequest", description = "Permite cargar saldo de una tarjeta")
public class CargarTarjetaRequest {
    @Schema(
            name = "cardId",
            description = "Id de la tarjeta a cargar",
            example = "1234567891234567",
            allowableValues = {"Number"},
            required = true)
    private long cardId;

    @Schema(
            name = "balance",
            description = "Valor a cargar",
            example = "1234567891234567",
            allowableValues = {"Number"},
            required = true)
    private long balance;

    public long getCardId() {
        return cardId;
    }

    public long getBalance() {
        return balance;
    }
}
