package dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs;

import dev.marcus.picPaySimplificado.domain.entities.transaction.Transaction;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioOutDTO;

public record TransactionOutDTO(
    UsuarioOutDTO usuarioPagante,
    UsuarioOutDTO usuarioRecebedor,
    Double valor
) {
    public TransactionOutDTO(
        UsuarioOutDTO usuarioPagante,
        UsuarioOutDTO usuarioRecebedor,
        Transaction transaction
    ){
        this(
            usuarioPagante,
            usuarioRecebedor,
            transaction.getValor()
        );
    }    
}
