package dev.marcus.picPaySimplificado.domain.entities.userComum.DTOs.impls;

import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.userComum.UserComum;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Roles;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioOutDTO;

public record UserComumOutDTOImpl(
    UUID id,
    Roles role,
    String nome,
    String email,
    String cpf,
    float saldo
) implements UsuarioOutDTO{
    public UserComumOutDTOImpl(UserComum userComum){
        this(
            userComum.getId(),
            userComum.getRole(),
            userComum.getNome(),
            userComum.getEmail(),
            userComum.getCpf(),
            userComum.getSaldo()
        );
    }   
}
