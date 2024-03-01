package com.prueba.dev.domain.ports.api;

import com.prueba.dev.domain.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long>{

}
