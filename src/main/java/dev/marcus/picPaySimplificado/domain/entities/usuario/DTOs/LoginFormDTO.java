package dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginFormDTO(
    @Email
    @NotBlank
    String email,
    @NotBlank
    String senha
) {
    
}
