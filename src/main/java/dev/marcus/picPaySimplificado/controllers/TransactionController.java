package dev.marcus.picPaySimplificado.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionDTO;
import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionOutDTO;
import dev.marcus.picPaySimplificado.services.interfaces.TransactionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;

    @PostMapping()
    @Transactional
    public ResponseEntity<TransactionOutDTO> createTransaction(
        @RequestBody @Valid TransactionDTO transactionData
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(transactionData, userEmail));
    }

    @GetMapping()
    public ResponseEntity<List<TransactionOutDTO>> getTransactions() {
        return ResponseEntity.ok().body(transactionService.getTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionOutDTO> getTransaction(@PathVariable UUID id) {
        return ResponseEntity.ok().body(transactionService.getTransaction(id));
    }
    
    
}
