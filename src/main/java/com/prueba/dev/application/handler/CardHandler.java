package com.prueba.dev.application.handler;

import com.prueba.dev.application.data.CargarTarjetaRequest;
import com.prueba.dev.domain.model.Tarjeta;
import com.prueba.dev.domain.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardHandler {

    @Autowired
    CardService cardService;

    public List<Tarjeta> test(){
        return cardService.test();
    }

    public String generarTarjeta(long idTarjeta){
        String response = null;
        int existe = cardService.idTarjetaValido(idTarjeta);
        if(existe == 0){
            String tarjeta = cardService.generarTarjeta(idTarjeta);
            response = ("La tarjeta se creada exitosamente, su tarjeta es: " + tarjeta);
        } else{
            if(existe == 1){
                response = ("El ID ingresado no es valido");
            }
            if(existe == 2){
                response = ("El ID ingresado ya existe");
            }
        }
        return response;
    }

    public String activarTarjeta(long tarjeta){
        return cardService.activarTarjeta(tarjeta);
    }

    public String bloquearTarjeta(long tarjeta){
        return cardService.bloquearTarjeta(tarjeta);
    }

    public String cargarTarjeta(CargarTarjetaRequest request){
        long idTarjeta = request.getCardId();
        long saldo = request.getBalance();
        return cardService.cargarTarjeta(idTarjeta, saldo);
    }

    public String consultarSaldo(long tarjeta){
        return cardService.consultarSaldo(tarjeta);
    }

}