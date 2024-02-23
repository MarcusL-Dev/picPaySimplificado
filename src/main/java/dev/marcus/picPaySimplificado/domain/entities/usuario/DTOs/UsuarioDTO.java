package dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import dev.marcus.picPaySimplificado.domain.entities.logista.DTOs.impls.LogistaDTOImpl;
import dev.marcus.picPaySimplificado.domain.entities.userComum.DTOs.impls.UserComumDTOImpl;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Roles;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "typeUser")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LogistaDTOImpl.class, name = "LOGISTA"),
        @JsonSubTypes.Type(value = UserComumDTOImpl.class, name = "COMUM")
})
public interface UsuarioDTO {
    Roles getRoleUser();
    String getNome();
    String getEmail();
    String getSenha();
    String getCnpjOrCpf();
}
