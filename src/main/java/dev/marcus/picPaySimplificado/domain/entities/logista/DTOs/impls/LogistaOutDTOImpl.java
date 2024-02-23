package dev.marcus.picPaySimplificado.domain.entities.logista.DTOs.impls;

import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.logista.Logista;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Roles;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioOutDTO;

public record LogistaOutDTOImpl(
    UUID id,
    Roles role,
    String nome,
    String email,
    String cnpj,
    float saldo
) implements UsuarioOutDTO{
    public LogistaOutDTOImpl(Logista logista){
        this(
            logista.getId(),
            logista.getRole(),
            logista.getNome(),
            logista.getEmail(),
            logista.getCnpj(),
            logista.getSaldo()
        );
    } 
}
