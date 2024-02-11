package dev.marcus.picPaySimplificado.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.marcus.picPaySimplificado.domain.entities.logista.Logista;

public interface LogistaRepository extends JpaRepository<Logista, UUID>{
    
}
