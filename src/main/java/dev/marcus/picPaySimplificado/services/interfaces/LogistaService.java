package dev.marcus.picPaySimplificado.services.interfaces;

import java.util.List;
import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.logista.DTOs.impls.LogistaOutDTOImpl;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioDTO;

public interface LogistaService {
    List<LogistaOutDTOImpl> getLogistas();
    LogistaOutDTOImpl getLogista(UUID id);
    LogistaOutDTOImpl createLogista(UsuarioDTO logistaData);
}
