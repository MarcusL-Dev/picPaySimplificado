package dev.marcus.picPaySimplificado.domain.entities.logista.DTOs;

import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.logista.Logista;

public record LogistaOutDTO(
    UUID id,
    String nome,
    String email,
    String cnpj
) {
    public LogistaOutDTO(Logista logista){
        this(
            logista.getId(),
            logista.getNome(),
            logista.getEmail(),
            logista.getCnpj()
        );
    } 
}
