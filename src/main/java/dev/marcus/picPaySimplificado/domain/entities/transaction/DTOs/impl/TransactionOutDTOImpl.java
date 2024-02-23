package dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.impl;

import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.transaction.Transaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.TypeTransaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionOutDTO;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioOutDTO;

public record TransactionOutDTOImpl(
    UUID id,
    TypeTransaction typeTransaction,
    UsuarioOutDTO usuarioData,
    float valor

) implements TransactionOutDTO{
    public TransactionOutDTOImpl(
        UsuarioOutDTO usuarioData,
        Transaction transaction
    ){
        this(
            transaction.getId(),
            transaction.getTypeTransaction(),
            usuarioData,
            transaction.getValor()
        );
    }
}
