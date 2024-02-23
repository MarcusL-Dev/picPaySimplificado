package dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.impl;

import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.transaction.TypeTransaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionDTO;
import jakarta.validation.constraints.NotNull;

public record SaqueDTOImpl(
    TypeTransaction typeTransaction,
    @NotNull(message = "valor nao pode ser nulo")
    float valor
) implements TransactionDTO{

    @Override
    public TypeTransaction getTypeTransaction() {
        return TypeTransaction.SAQUE;
    }

    @Override
    public UUID getIdUsuarioRecebedor() {
        return null;
    }

    @Override
    public float getValor() {
        return this.valor;
    }
    
}
