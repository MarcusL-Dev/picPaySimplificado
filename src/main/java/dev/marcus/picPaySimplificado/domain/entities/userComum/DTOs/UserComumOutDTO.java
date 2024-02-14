package dev.marcus.picPaySimplificado.domain.entities.userComum.DTOs;

import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.userComum.UserComum;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Roles;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioOutDTO;

public record UserComumOutDTO(
    UUID id,
    Roles role,
    String nome,
    String email,
    String cpf
) implements UsuarioOutDTO{
    public UserComumOutDTO(UserComum userComum){
        this(
            userComum.getId(),
            userComum.getRole(),
            userComum.getNome(),
            userComum.getEmail(),
            userComum.getCpf()
        );
    }   
}
