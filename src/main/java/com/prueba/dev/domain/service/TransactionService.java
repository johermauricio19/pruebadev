package com.prueba.dev.domain.service;

import com.prueba.dev.domain.model.Tarjeta;
import com.prueba.dev.domain.model.Transaction;
import com.prueba.dev.domain.ports.api.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardService cardService;

    public Optional<Transaction> consultarTransaccion(long idTransaccion){
        return transactionRepository.findById(idTransaccion);
    }

    public String compra(long idTarjeta, long valor){
        String response;
        long id = cardService.dividirTarjeta(idTarjeta);
        var existe = cardService.buscarTarjeta(id);
        if(existe.isEmpty()){
            response = ("La tarjeta ingresada no existe");
        }else {
            Tarjeta tarjeta = existe.get();
            String validar = String.valueOf(tarjeta.getId_tarjeta()) + String.valueOf(tarjeta.getNumero());
            if (validar.equals(String.valueOf(idTarjeta))) {
                if(tarjeta.isBloqueada() || !tarjeta.isActiva()){
                    return "La tarjeta esta bloqueada o no esta activa";
                }
                Date hoy = new Date();
                if(hoy.after(tarjeta.getVence())){
                    return "Su tarjeta esta vencida";
                }
                if(tarjeta.getSaldo()<valor){
                    return "No tiene saldo suficiente para la compra";
                }else{
                    agregarTransaccion(tarjeta, valor);
                    tarjeta.setSaldo(tarjeta.getSaldo()-valor);
                    cardService.guardarTarjeta(tarjeta);
                    response = "La compra fue exitosa";
                }

            }else {
                response = ("La tarjeta ingresada no es correcta");
            }
        }
        return response;
    }

    public void agregarTransaccion(Tarjeta tarjeta, long valor){
        Transaction transaction = new Transaction();
        transaction.setId_tarjeta(tarjeta);
        transaction.setFecha(new Date());
        transaction.setValor(valor);
        transaction.setAnulada(false);
        transactionRepository.save(transaction);
    }

    public String anular(long cardId, long transactionId){
        var transaccion = consultarTransaccion(transactionId);
        String response;
        long id = cardService.dividirTarjeta(cardId);
        var existe = cardService.buscarTarjeta(id);
        if(existe.isEmpty()){
            response = ("La tarjeta ingresada no existe");
        }else {
            Tarjeta tarjeta = existe.get();
            String validar = String.valueOf(tarjeta.getId_tarjeta()) + String.valueOf(tarjeta.getNumero());
            if (validar.equals(String.valueOf(cardId))) {
                if(transaccion.isEmpty()){
                    return "La transaccion no existe";
                }else{
                    Transaction transaction = transaccion.get();
                    long reintegro = transaction.getValor();
                    transaction.setAnulada(true);
                    tarjeta.setSaldo(tarjeta.getSaldo()+reintegro);
                    transactionRepository.save(transaction);
                    cardService.guardarTarjeta(tarjeta);
                    response = "La transacción fue anulada y el monto reintegrado";
                }
            }else {
                response = ("La tarjeta ingresada no es correcta");
            }
        }
        return response;
    }

}
