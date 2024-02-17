package dev.marcus.picPaySimplificado.domain.entities.transfer.DTOs;

import java.math.BigDecimal;

import dev.marcus.picPaySimplificado.domain.entities.transfer.Transfer;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioOutDTO;

public record TransferOutDTO(
    UsuarioOutDTO usuarioPagante,
    UsuarioOutDTO usuarioRecebedor,
    BigDecimal valor
) {
    public TransferOutDTO(
        UsuarioOutDTO usuarioPagante,
        UsuarioOutDTO usuarioRecebedor,
        Transfer transfer
    ){
        this(
            usuarioPagante,
            usuarioRecebedor,
            transfer.getValor()
        );
    }    
}
