package dev.marcus.picPaySimplificado.services.interfaces;

import java.util.List;
import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.usuario.Usuario;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioDTO;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioOutDTO;

public interface UsuarioService {
    List<UsuarioOutDTO> getUsers();
    UsuarioOutDTO transforUsuarioToUsuarioDTO(Usuario usuario);
    Usuario getUsuario(UUID id);
    UsuarioOutDTO getUsuarioData(UUID id);
    Usuario getUsuarioByEmail(String userEmail);
    UsuarioOutDTO createUser(UsuarioDTO usuarioData);
}
