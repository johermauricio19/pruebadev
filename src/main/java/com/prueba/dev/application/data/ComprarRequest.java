package com.prueba.dev.application.data;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ComprarRequest", description = "Permite comprar con una tarjeta")
public class ComprarRequest {
    @Schema(
            name = "cardId",
            description = "Id de la tarjeta a usar",
            example = "1234567891234567",
            allowableValues = {"Number"},
            required = true)
    private long cardId;

    @Schema(
            name = "price",
            description = "Valor de compra",
            example = "1234567891234567",
            allowableValues = {"Number"},
            required = true)
    private long price;

    public long getCardId() {
        return cardId;
    }

    public long getPrice() {
        return price;
    }
}
