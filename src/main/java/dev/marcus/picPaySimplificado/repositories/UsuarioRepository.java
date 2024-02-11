package dev.marcus.picPaySimplificado.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import dev.marcus.picPaySimplificado.domain.entities.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
    UserDetails findByEmail(String email);
}