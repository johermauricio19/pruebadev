package com.prueba.dev.domain.ports.api;

import com.prueba.dev.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
