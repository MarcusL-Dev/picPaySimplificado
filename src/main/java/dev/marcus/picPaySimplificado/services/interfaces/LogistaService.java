package dev.marcus.picPaySimplificado.services.interfaces;

import java.util.List;
import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.logista.DTOs.LogistaDTO;
import dev.marcus.picPaySimplificado.domain.entities.logista.DTOs.LogistaOutDTO;

public interface LogistaService {
    List<LogistaOutDTO> getLogistas();
    LogistaOutDTO getLogista(UUID id);
    LogistaOutDTO createLogista(LogistaDTO logistaData);
}
