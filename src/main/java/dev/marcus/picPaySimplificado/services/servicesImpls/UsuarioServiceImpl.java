package dev.marcus.picPaySimplificado.services.servicesImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.marcus.picPaySimplificado.domain.entities.usuario.Roles;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Usuario;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioDTO;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioOutDTO;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.EntityNotFoundException;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.TypeEntities;
import dev.marcus.picPaySimplificado.repositories.UsuarioRepository;
import dev.marcus.picPaySimplificado.services.interfaces.LogistaService;
import dev.marcus.picPaySimplificado.services.interfaces.UserComumService;
import dev.marcus.picPaySimplificado.services.interfaces.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UserComumService userComumService;
    @Autowired
    private LogistaService logistaService;

    @Override
    public UsuarioOutDTO transforUsuarioToUsuarioDTO(Usuario usuario){
        if (usuario.getRole() == Roles.COMUM) {
            return userComumService.getUserComum(usuario.getId());
        }else{
            return logistaService.getLogista(usuario.getId()); 
        }
    }

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

    @Override
    public List<UsuarioOutDTO> getUsers() {
        var users = this.usuarioRepository.findAll();
        var usersOutData = new ArrayList<UsuarioOutDTO>();
        for(Usuario user : users){
            var userOutData = this.transforUsuarioToUsuarioDTO(user);
            usersOutData.add(userOutData);
        }
        return usersOutData;
    }

    @Override
    public UsuarioOutDTO getUsuarioData(UUID id) {
        var usuario = this.getUsuario(id);
        return transforUsuarioToUsuarioDTO(usuario);
    }

    @Override
    public UsuarioOutDTO createUser(UsuarioDTO usuarioData) {
        if(usuarioData.getRoleUser() == Roles.LOGISTA){
            return logistaService.createLogista(usuarioData);
        }else{
            return userComumService.createUserComum(usuarioData);
        }
    }
}
