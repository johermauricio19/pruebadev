package com.prueba.dev.application.handler;

import com.prueba.dev.application.data.AnularCompraRequest;
import com.prueba.dev.application.data.ComprarRequest;
import com.prueba.dev.domain.model.Tarjeta;
import com.prueba.dev.domain.model.Transaction;
import com.prueba.dev.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransactionHandler {

    @Autowired
    private TransactionService transactionService;

    public Optional<Transaction> consultarTransaccion(long idTransaccion){
        return transactionService.consultarTransaccion(idTransaccion);
    }

    public String compra(ComprarRequest request){
        long idTarjeta = request.getCardId();
        long valor = request.getPrice();
        return transactionService.compra(idTarjeta, valor);
    }

    public String anular(AnularCompraRequest request){
        long cardId = request.getCardId();
        long transactionId = request.getTransactionId();
        return transactionService.anular(cardId, transactionId);
    }

}