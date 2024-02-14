package dev.marcus.picPaySimplificado.domain.entities.logista.DTOs;

import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.logista.Logista;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Roles;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioOutDTO;

public record LogistaOutDTO(
    UUID id,
    Roles role,
    String nome,
    String email,
    String cnpj
) implements UsuarioOutDTO{
    public LogistaOutDTO(Logista logista){
        this(
            logista.getId(),
            logista.getRole(),
            logista.getNome(),
            logista.getEmail(),
            logista.getCnpj()
        );
    } 
}
