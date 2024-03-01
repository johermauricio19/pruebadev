package com.prueba.dev.domain.service;

import com.prueba.dev.domain.model.Tarjeta;
import com.prueba.dev.domain.ports.api.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CardService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    public List<Tarjeta> test(){
        List<Tarjeta> list = tarjetaRepository.findAll();
        return list;
    }

    public Optional<Tarjeta> buscarTarjeta(long idTarjeta){
        return tarjetaRepository.findById(idTarjeta);
    }

    public int idTarjetaValido(long idTarjeta){
        if(String.valueOf(idTarjeta).length() != 6){
            return 1;
        }
        var existe = buscarTarjeta(idTarjeta);
        if(!existe.isEmpty()){
            return 2;
        }
        return 0;
    }

    public Long generarNumeroTarjeta(){
        Random random = new Random();
        long numeroAleatorio = (long) (random.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        return numeroAleatorio;
    }

    public Date vence(){
        Date fecha = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.YEAR, 3);
        Date vence = calendar.getTime();
        return vence;
    }

    public Long dividirTarjeta(long tarjeta){
        String id = String.valueOf(tarjeta);
        String inicio = id.substring(0, 6);
        Long salida =  Long.parseLong(inicio);
        return salida;
    }

    public String generarTarjeta(long id){
        Tarjeta tarjeta = new Tarjeta();
        long numero = generarNumeroTarjeta();
        tarjeta.setId_tarjeta(id);
        tarjeta.setNumero(numero);
        tarjeta.setTitular("Pendiente");
        tarjeta.setCreada(new Date());
        tarjeta.setVence(vence());
        tarjeta.setActiva(false);
        tarjeta.setBloqueada(false);
        tarjeta.setSaldo(0);
        guardarTarjeta(tarjeta);
        return ""+id+numero;
    }

    public void guardarTarjeta(Tarjeta tarjeta){
        tarjetaRepository.save(tarjeta);
    }

    public String activarTarjeta(long idTarjeta){
        String response;
        long id = dividirTarjeta(idTarjeta);
        var existe = buscarTarjeta(id);
        if(existe.isEmpty()){
            response = ("La tarjeta ingresada no existe");
        }else{
            Tarjeta tarjeta = existe.get();
            String validar = String.valueOf(tarjeta.getId_tarjeta()) + String.valueOf(tarjeta.getNumero());
            if(validar.equals(String.valueOf(idTarjeta))){
                tarjeta.setActiva(true);
                guardarTarjeta(tarjeta);
                response = ("La tarjeta " + idTarjeta + " ha sido activada exitosamente.");
            }else {
                response = ("La tarjeta ingresada no es correcta");
            }
        }
        return response;
    }

    public String bloquearTarjeta(long idTarjeta){
        String response;
        long id = dividirTarjeta(idTarjeta);
        var existe = buscarTarjeta(id);
        if(existe.isEmpty()){
            response = ("La tarjeta ingresada no existe");
        }else{
            Tarjeta tarjeta = existe.get();
            String validar = String.valueOf(tarjeta.getId_tarjeta()) + String.valueOf(tarjeta.getNumero());
            if(validar.equals(String.valueOf(idTarjeta))){
                tarjeta.setBloqueada(true);
                guardarTarjeta(tarjeta);
                response = ("La tarjeta " + idTarjeta + " ha sido bloqueada exitosamente.");
            }else {
                response = ("La tarjeta ingresada no es correcta");
            }
        }
        return response;
    }

    public String cargarTarjeta(long idTarjeta, long carga){
        String response;
        if(carga < 1){
            return "El saldo a cargar no es valido";
        }
        long id = dividirTarjeta(idTarjeta);
        var existe = buscarTarjeta(id);
        if(existe.isEmpty()){
            response = ("La tarjeta ingresada no existe");
        }else{
            Tarjeta tarjeta = existe.get();
            String validar = String.valueOf(tarjeta.getId_tarjeta()) + String.valueOf(tarjeta.getNumero());
            if(validar.equals(String.valueOf(idTarjeta))){
                if(!tarjeta.isActiva()){
                    return "La tarjeta no esta activa";
                }
                if(tarjeta.isBloqueada()){
                    return "La tarjeta se encuentra bloqueada";
                }
                long nuevoSaldo = tarjeta.getSaldo();
                nuevoSaldo = nuevoSaldo + carga;
                tarjeta.setSaldo(nuevoSaldo);
                guardarTarjeta(tarjeta);
                response = ("La tarjeta " + idTarjeta + " ha sido cargada con: " + nuevoSaldo + " exitosamente." );
            }else {
                response = ("La tarjeta ingresada no es correcta");
            }
        }
        return response;
    }

    public String consultarSaldo(long idTarjeta){
        String response;
        long id = dividirTarjeta(idTarjeta);
        var existe = buscarTarjeta(id);
        if(existe.isEmpty()){
            response = ("La tarjeta ingresada no existe");
        }else{
            Tarjeta tarjeta = existe.get();
            String validar = String.valueOf(tarjeta.getId_tarjeta()) + String.valueOf(tarjeta.getNumero());
            if(validar.equals(String.valueOf(idTarjeta))){
                long saldo = tarjeta.getSaldo();
                guardarTarjeta(tarjeta);
                response = ("La tarjeta " + idTarjeta + " tiene un saldo de: " + saldo );
            }else {
                response = ("La tarjeta ingresada no es correcta");
            }
        }
        return response;
    }

}
