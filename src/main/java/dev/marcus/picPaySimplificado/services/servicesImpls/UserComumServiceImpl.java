package dev.marcus.picPaySimplificado.services.servicesImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.marcus.picPaySimplificado.domain.entities.userComum.UserComum;
import dev.marcus.picPaySimplificado.domain.entities.userComum.DTOs.impls.UserComumOutDTOImpl;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioDTO;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.EntityNotFoundException;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.TypeEntities;
import dev.marcus.picPaySimplificado.repositories.UserComumRepository;
import dev.marcus.picPaySimplificado.services.interfaces.UserComumService;

@Service
public class UserComumServiceImpl implements UserComumService{

    @Autowired
    private UserComumRepository userComumRepository;

    @Override
    public List<UserComumOutDTOImpl> getUsersComuns() {
        var usersComunsOutData = new ArrayList<UserComumOutDTOImpl>();
        var usersComuns = this.userComumRepository.findAll();
        for(UserComum userComum: usersComuns){
            var userComumOutData = new UserComumOutDTOImpl(userComum);
            usersComunsOutData.add(userComumOutData);
        }
        return usersComunsOutData;  
    }

    @Override
    public UserComumOutDTOImpl createUserComum(UsuarioDTO userComumData) {
        String encryptedSenha = new BCryptPasswordEncoder().encode(userComumData.getSenha());
        var newUserComum = new UserComum(userComumData, encryptedSenha);
        this.userComumRepository.save(newUserComum);
        var userComumOutData = new UserComumOutDTOImpl(newUserComum);
        return userComumOutData;
    }

    @Override
    public UserComumOutDTOImpl getUserComum(UUID id) {
        @SuppressWarnings("null")
        var userComum = userComumRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(TypeEntities.USUARIO_COMUM, id));
        var userComumOutData = new UserComumOutDTOImpl(userComum);
        return userComumOutData;
    }
    
}
