package dev.marcus.picPaySimplificado.domain.entities.userComum.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserComumDTO(
    @NotBlank
    String nome,
    @NotBlank
    @Email
    String email,
    @NotBlank
    String senha,
    @NotBlank
    String cpf
) {
    
}
