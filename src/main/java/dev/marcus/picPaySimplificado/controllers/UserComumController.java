package dev.marcus.picPaySimplificado.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.marcus.picPaySimplificado.domain.entities.userComum.DTOs.UserComumDTO;
import dev.marcus.picPaySimplificado.domain.entities.userComum.DTOs.UserComumOutDTO;
import dev.marcus.picPaySimplificado.services.interfaces.UserComumService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/usercomum")
public class UserComumController {
    
    @Autowired
    private UserComumService userComumService;

    @GetMapping()
    public ResponseEntity<List<UserComumOutDTO>> getUsersComuns() {
        return ResponseEntity.ok().body(userComumService.getUsersComuns());
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<UserComumOutDTO> createUserComum(@RequestBody @Valid UserComumDTO userComumData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userComumService.createUserComum(userComumData));
    }
    

}
