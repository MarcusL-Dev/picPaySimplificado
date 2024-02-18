package dev.marcus.picPaySimplificado.services.interfaces;

import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionDTO;
import dev.marcus.picPaySimplificado.domain.entities.transfer.Transfer;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Usuario;

public interface TransferService {
    Transfer creteTransfer(TransactionDTO transactionData, Usuario usuario, Usuario usuarioRecebedor);
    Transfer getTransfer(UUID id);
}
