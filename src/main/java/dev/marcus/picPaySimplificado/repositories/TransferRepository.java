package dev.marcus.picPaySimplificado.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.marcus.picPaySimplificado.domain.entities.transfer.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, UUID>{
    
}
