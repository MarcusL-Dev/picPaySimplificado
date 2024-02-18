package dev.marcus.picPaySimplificado.domain.entities.transfer.DTOs.impl;

import java.math.BigDecimal;
import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.transaction.Transaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.TypeTransaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionOutDTO;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioOutDTO;

public record TransferOutDTOImpl(
    UUID id,
    TypeTransaction typeTransaction,
    UsuarioOutDTO usuario,
    UsuarioOutDTO usuarioRecebedor,
    BigDecimal valor

) implements TransactionOutDTO{

    public TransferOutDTOImpl(
        UsuarioOutDTO usuario,
        UsuarioOutDTO usuarioRecebedor,
        Transaction transaction
    ){
        this(
            transaction.getId(),
            transaction.getTypeTransaction(),
            usuario,
            usuarioRecebedor,
            transaction.getValor()
        );
    }

}
