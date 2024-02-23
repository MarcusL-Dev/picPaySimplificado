package dev.marcus.picPaySimplificado.services.interfaces;

import java.util.List;
import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.userComum.DTOs.impls.UserComumOutDTOImpl;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioDTO;

public interface UserComumService {
    List<UserComumOutDTOImpl> getUsersComuns();
    UserComumOutDTOImpl createUserComum(UsuarioDTO userComumData);
    UserComumOutDTOImpl getUserComum(UUID id);
}
