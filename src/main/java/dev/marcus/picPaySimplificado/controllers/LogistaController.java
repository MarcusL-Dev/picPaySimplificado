package dev.marcus.picPaySimplificado.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.marcus.picPaySimplificado.domain.entities.logista.DTOs.LogistaDTO;
import dev.marcus.picPaySimplificado.domain.entities.logista.DTOs.LogistaOutDTO;
import dev.marcus.picPaySimplificado.services.interfaces.LogistaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/logistas")
public class LogistaController {

    @Autowired
    private LogistaService logistaService;
    
    @GetMapping()
    public ResponseEntity<List<LogistaOutDTO>> getLogistas() {
        return ResponseEntity.ok().body(logistaService.getLogistas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogistaOutDTO> getLogista(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(logistaService.getLogista(id));
    }
    
    @PostMapping()
    @Transactional
    public ResponseEntity<LogistaOutDTO> createLogista(@RequestBody @Valid LogistaDTO logistaData) {
        return ResponseEntity.ok().body(logistaService.createLogista(logistaData));
    }
}
