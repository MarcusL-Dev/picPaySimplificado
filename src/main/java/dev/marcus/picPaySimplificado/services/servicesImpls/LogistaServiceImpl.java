package dev.marcus.picPaySimplificado.services.servicesImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.marcus.picPaySimplificado.domain.entities.logista.Logista;
import dev.marcus.picPaySimplificado.domain.entities.logista.DTOs.LogistaDTO;
import dev.marcus.picPaySimplificado.domain.entities.logista.DTOs.LogistaOutDTO;
import dev.marcus.picPaySimplificado.repositories.LogistaRepository;
import dev.marcus.picPaySimplificado.services.interfaces.LogistaService;

@Service
public class LogistaServiceImpl implements LogistaService{

    @Autowired
    private LogistaRepository logistaRepository;

    @Override
    public List<LogistaOutDTO> getLogistas() {
        var logistasOutData = new ArrayList<LogistaOutDTO>();
        var logistas = this.logistaRepository.findAll();
        for(Logista logista: logistas){
            var logistaOutData = new LogistaOutDTO(logista);
            logistasOutData.add(logistaOutData);
        }
        return logistasOutData;
    }

    @Override
    public LogistaOutDTO createLogista(LogistaDTO logistaData) {
        String encryptedSenha = new BCryptPasswordEncoder().encode(logistaData.senha());
        var newLogista = new Logista(logistaData, encryptedSenha);
        this.logistaRepository.save(newLogista);
        var logistaOutData = new LogistaOutDTO(newLogista);
        return logistaOutData;
    }
    
}
