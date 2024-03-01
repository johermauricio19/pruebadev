package com.prueba.dev.application.data;

import com.prueba.dev.domain.util.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ActivarTarjetaRequest", description = "Permite la activación de una tarjeta")
public class ActivarTarjetaRequest {
    @Schema(
            name = "cardId",
            description = "Id de la tarjeta a activar",
            example = "1234567891234567",
            allowableValues = {"Number"},
            required = true)
    private long cardId;

    public long getCardId() {
        return cardId;
    }
}
