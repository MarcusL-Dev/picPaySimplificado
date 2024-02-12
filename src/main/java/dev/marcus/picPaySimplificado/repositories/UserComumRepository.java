package dev.marcus.picPaySimplificado.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.marcus.picPaySimplificado.domain.entities.userComum.UserComum;

public interface UserComumRepository extends JpaRepository<UserComum, UUID>{
    
}
