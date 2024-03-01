package com.prueba.dev.application.data;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AnularCompraRequest", description = "Permite anular compra de una tarjeta")
public class AnularCompraRequest {
    @Schema(
            name = "cardId",
            description = "Id de la tarjeta a usar",
            example = "1234567891234567",
            allowableValues = {"Number"},
            required = true)
    private long cardId;

    @Schema(
            name = "transactionId",
            description = "Id de transacción a anular",
            example = "1234567891234567",
            allowableValues = {"Number"},
            required = true)
    private long transactionId;

    public long getCardId() {
        return cardId;
    }

    public long getTransactionId() {
        return transactionId;
    }
}
