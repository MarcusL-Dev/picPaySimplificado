package dev.marcus.picPaySimplificado.services.servicesImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.marcus.picPaySimplificado.domain.entities.logista.Logista;
import dev.marcus.picPaySimplificado.domain.entities.logista.DTOs.impls.LogistaOutDTOImpl;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioDTO;
import dev.marcus.picPaySimplificado.repositories.LogistaRepository;
import dev.marcus.picPaySimplificado.services.interfaces.LogistaService;

@Service
public class LogistaServiceImpl implements LogistaService{

    @Autowired
    private LogistaRepository logistaRepository;

    @Override
    public List<LogistaOutDTOImpl> getLogistas() {
        var logistasOutData = new ArrayList<LogistaOutDTOImpl>();
        var logistas = this.logistaRepository.findAll();
        for(Logista logista: logistas){
            var logistaOutData = new LogistaOutDTOImpl(logista);
            logistasOutData.add(logistaOutData);
        }
        return logistasOutData;
    }

    @Override
    public LogistaOutDTOImpl createLogista(UsuarioDTO logistaData) {
        String encryptedSenha = new BCryptPasswordEncoder().encode(logistaData.getSenha());
        var newLogista = new Logista(logistaData, encryptedSenha);
        this.logistaRepository.save(newLogista);
        var logistaOutData = new LogistaOutDTOImpl(newLogista);
        return logistaOutData;
    }

    @Override
    public LogistaOutDTOImpl getLogista(UUID id) {
        @SuppressWarnings("null")
        var logista = logistaRepository.findById(id).get();
        var logistaOutData = new LogistaOutDTOImpl(logista);
        return logistaOutData;
    }
}
