package dev.marcus.picPaySimplificado.domain.entities.transfer.DTOs.impl;

import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.transaction.TypeTransaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionDTO;
import jakarta.validation.constraints.NotNull;

public record TransferDTOImpl(
    @NotNull(message = "idUsuarioRecebedor nao pode ser nulo")
    UUID idUsuarioRecebedor,
    @NotNull(message = "valor nao pode ser nulo")
    float valor
) implements TransactionDTO{

    @Override
    public TypeTransaction getTypeTransaction() {
        return TypeTransaction.TRANSFER;
    }

    @Override
    public UUID getIdUsuarioRecebedor() {
        return this.idUsuarioRecebedor;
    }

    @Override
    public float getValor() {
        return this.valor;
    }
    
}
