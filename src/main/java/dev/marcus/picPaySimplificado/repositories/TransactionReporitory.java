package dev.marcus.picPaySimplificado.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.marcus.picPaySimplificado.domain.entities.transaction.Transaction;

public interface TransactionReporitory extends JpaRepository<Transaction, UUID>{

}
