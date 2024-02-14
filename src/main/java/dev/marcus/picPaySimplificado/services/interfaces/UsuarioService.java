package dev.marcus.picPaySimplificado.services.interfaces;

import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.usuario.Usuario;

public interface UsuarioService {
    Usuario getUsuario(UUID id);
    Usuario getUsuarioByEmail(String userEmail);
}
