package dev.marcus.picPaySimplificado.domain.entities.transfer.DTOs.impl;

import java.math.BigDecimal;

import dev.marcus.picPaySimplificado.domain.entities.transaction.Transaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.TypeTransaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionOutDTO;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioOutDTO;

public record TransferOutDTOImpl(
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
            transaction.getTypeTransaction(),
            usuario,
            usuarioRecebedor,
            transaction.getValor()
        );
    }

}
