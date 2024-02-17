package dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs;

import java.math.BigDecimal;
import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.transaction.TypeTransaction;
import jakarta.validation.constraints.NotNull;

public record TransactionDTO(
    @NotNull
    UUID idUsuarioRecebedor,
    @NotNull
    BigDecimal valor,
    @NotNull
    TypeTransaction typeTransaction
) {
    
}
