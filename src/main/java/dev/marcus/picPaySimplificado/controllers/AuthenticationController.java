package dev.marcus.picPaySimplificado.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.marcus.picPaySimplificado.domain.entities.usuario.Usuario;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.LoginFormDTO;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.LoginResponseDTO;
import dev.marcus.picPaySimplificado.infra.springSecurityConfig.TokenService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping()
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginFormDTO loginFormData) {
        var userPassword = new UsernamePasswordAuthenticationToken(loginFormData.email(), loginFormData.senha());
        var auth = authenticationManager.authenticate(userPassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        var loginResponse = new LoginResponseDTO(token);

        return ResponseEntity.ok().body(loginResponse);
    }
    
}
