package dev.marcus.picPaySimplificado.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioDTO;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioOutDTO;
import dev.marcus.picPaySimplificado.services.interfaces.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<UsuarioOutDTO>> getUsers() {
        return ResponseEntity.ok().body(usuarioService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioOutDTO> getUser(@PathVariable("id") UUID id) {
        return  ResponseEntity.ok().body(usuarioService.getUsuarioData(id));
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<UsuarioOutDTO> createUser(@RequestBody @Valid UsuarioDTO usuarioData) {  
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUser(usuarioData));
    }
    
    
}
