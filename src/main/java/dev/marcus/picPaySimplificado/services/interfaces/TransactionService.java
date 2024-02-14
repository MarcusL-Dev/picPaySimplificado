package dev.marcus.picPaySimplificado.services.interfaces;

import java.util.List;
import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionDTO;
import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionOutDTO;

public interface TransactionService {
    List<TransactionOutDTO> getTransactions();
    TransactionOutDTO createTransaction(TransactionDTO transactionData, String userEmail);
    TransactionOutDTO getTransaction(UUID id);

}
