package dev.marcus.picPaySimplificado.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.LoginFormDTO;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping()
    public ResponseEntity login(@RequestBody @Valid LoginFormDTO loginFormData) {
        var userPassword = new UsernamePasswordAuthenticationToken(loginFormData.email(), loginFormData.senha());
        var auth = authenticationManager.authenticate(userPassword);
        
        return ResponseEntity.ok().build();
    }
    
}
