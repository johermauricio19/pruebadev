package com.prueba.dev.application.controller;

import java.util.List;

import com.prueba.dev.application.data.ActivarTarjetaRequest;
import com.prueba.dev.application.data.CargarTarjetaRequest;
import com.prueba.dev.application.handler.CardHandler;
import com.prueba.dev.domain.model.Tarjeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/card")
public class CardController {

    @Autowired
    private CardHandler cardHandler;


    @GetMapping("/list")
    public List<Tarjeta> todasTarjetas() {
        return cardHandler.test();
    }

    @GetMapping("/{productId}/number")
    public String generarTarjeta (@PathVariable Long productId){
        return cardHandler.generarTarjeta(productId);
    }

    @PostMapping("/enroll")
    public String activarTarjeta(@RequestBody ActivarTarjetaRequest request){
        return cardHandler.activarTarjeta(request.getCardId());
    }

    @DeleteMapping("/{cardId}")
    public String bloquearTarjeta(@PathVariable Long cardId){
        return cardHandler.bloquearTarjeta(cardId);
    }

    @PostMapping("/balance")
    public String cargarTarjeta(@RequestBody CargarTarjetaRequest request){
        return cardHandler.cargarTarjeta(request);
    }

    @GetMapping("/balance/{cardId}")
    public String consultarSaldo(@PathVariable Long cardId){
        return cardHandler.consultarSaldo(cardId);
    }

}