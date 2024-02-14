package dev.marcus.picPaySimplificado.services.servicesImpls;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.marcus.picPaySimplificado.domain.entities.usuario.Usuario;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.EntityNotFoundException;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.TypeEntities;
import dev.marcus.picPaySimplificado.repositories.UsuarioRepository;
import dev.marcus.picPaySimplificado.services.interfaces.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario getUsuario(UUID id) {
        @SuppressWarnings("null")
        var usuario = usuarioRepository.findById(id);
        if (!usuario.isPresent()) {
            new EntityNotFoundException(TypeEntities.USUARIO, id);
        }
        return usuario.get();
    }

    @Override
    public Usuario getUsuarioByEmail(String userEmail) {
        return (Usuario) usuarioRepository.findByEmail(userEmail);
    }
}
