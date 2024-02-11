package dev.marcus.picPaySimplificado.domain.entities.logista.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LogistaDTO(
    @NotBlank
    String nome,
    @NotBlank
    @Email
    String email,
    @NotBlank
    String senha,
    @NotBlank
    String cnpj
) {
    
}
