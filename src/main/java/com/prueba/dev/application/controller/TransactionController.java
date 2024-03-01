package com.prueba.dev.application.controller;

import com.prueba.dev.application.data.AnularCompraRequest;
import com.prueba.dev.application.data.ComprarRequest;
import com.prueba.dev.application.handler.TransactionHandler;
import com.prueba.dev.domain.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

	@Autowired
    private TransactionHandler transactionHandler;

    @GetMapping("/{transactionId}")
    public Optional<Transaction> consultarTransaccion(@PathVariable Long transactionId) {
        return transactionHandler.consultarTransaccion(transactionId);
    }

    @PostMapping("/purchase")
   public String compra(@RequestBody ComprarRequest request) {
        return transactionHandler.compra(request);
    }

    @PostMapping("/anulation")
    public String anularTransaccion(@RequestBody AnularCompraRequest request) {
        return transactionHandler.anular(request);
    }

}