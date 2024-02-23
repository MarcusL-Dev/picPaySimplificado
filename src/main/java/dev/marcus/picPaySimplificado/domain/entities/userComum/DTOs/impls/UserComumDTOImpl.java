package dev.marcus.picPaySimplificado.domain.entities.userComum.DTOs.impls;

import dev.marcus.picPaySimplificado.domain.entities.usuario.Roles;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserComumDTOImpl(
    @NotBlank(message = "nome nao pode ser nulo")
    String nome,
    @NotBlank(message = "email nao pode ser nulo")
    @Email(message = "email nao valido")
    String email,
    @NotBlank(message = "senha nao pode ser nulo")
    String senha,
    @NotBlank(message = "cpf nao pode ser nulo")
    String cpf
) implements UsuarioDTO{

    @Override
    public Roles getRoleUser() {
        return Roles.COMUM;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getSenha() {
        return this.senha;
    }

    @Override
    public String getCnpjOrCpf() {
        return this.cpf;
    }    
}
