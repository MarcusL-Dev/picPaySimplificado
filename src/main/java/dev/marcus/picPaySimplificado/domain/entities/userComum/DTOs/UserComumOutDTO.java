package dev.marcus.picPaySimplificado.domain.entities.userComum.DTOs;

import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.userComum.UserComum;

public record UserComumOutDTO(
    UUID id,
    String nome,
    String email,
    String cpf
) {
    public UserComumOutDTO(UserComum userComum){
        this(
            userComum.getId(),
            userComum.getNome(),
            userComum.getEmail(),
            userComum.getCpf()
        );
    }   
}
