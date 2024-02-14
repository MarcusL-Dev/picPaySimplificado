package dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record TransactionDTO(
    @NotNull
    UUID idUsuarioRecebedor,
    @NotNull
    Double valor
) {
    
}
