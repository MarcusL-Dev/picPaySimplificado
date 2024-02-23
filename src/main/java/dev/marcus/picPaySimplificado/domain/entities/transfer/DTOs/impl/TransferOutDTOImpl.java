package dev.marcus.picPaySimplificado.domain.entities.transfer.DTOs.impl;

import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.transaction.Transaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.TypeTransaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionOutDTO;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioOutDTO;

public record TransferOutDTOImpl(
    UUID id,
    TypeTransaction typeTransaction,
    UsuarioOutDTO usuarioPagador,
    UsuarioOutDTO usuarioRecebedor,
    float valor

) implements TransactionOutDTO{

    public TransferOutDTOImpl(
        UsuarioOutDTO usuarioPagador,
        UsuarioOutDTO usuarioRecebedor,
        Transaction transaction
    ){
        this(
            transaction.getId(),
            transaction.getTypeTransaction(),
            usuarioPagador,
            usuarioRecebedor,
            transaction.getValor()
        );
    }

}
