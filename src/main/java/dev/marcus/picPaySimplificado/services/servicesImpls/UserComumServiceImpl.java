package dev.marcus.picPaySimplificado.services.servicesImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.marcus.picPaySimplificado.domain.entities.userComum.UserComum;
import dev.marcus.picPaySimplificado.domain.entities.userComum.DTOs.UserComumDTO;
import dev.marcus.picPaySimplificado.domain.entities.userComum.DTOs.UserComumOutDTO;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.EntityNotFoundException;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.TypeEntities;
import dev.marcus.picPaySimplificado.repositories.UserComumRepository;
import dev.marcus.picPaySimplificado.services.interfaces.UserComumService;

@Service
public class UserComumServiceImpl implements UserComumService{

    @Autowired
    private UserComumRepository userComumRepository;

    @Override
    public List<UserComumOutDTO> getUsersComuns() {
        var usersComunsOutData = new ArrayList<UserComumOutDTO>();
        var usersComuns = this.userComumRepository.findAll();
        for(UserComum userComum: usersComuns){
            var userComumOutData = new UserComumOutDTO(userComum);
            usersComunsOutData.add(userComumOutData);
        }
        return usersComunsOutData;  
    }

    @Override
    public UserComumOutDTO createUserComum(UserComumDTO userComumData) {
        String encryptedSenha = new BCryptPasswordEncoder().encode(userComumData.senha());
        var newUserComum = new UserComum(userComumData, encryptedSenha);
        this.userComumRepository.save(newUserComum);
        var userComumOutData = new UserComumOutDTO(newUserComum);
        return userComumOutData;
    }

    @Override
    public UserComumOutDTO getUserComum(UUID id) {
        @SuppressWarnings("null")
        var userComum = userComumRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(TypeEntities.USUARIO_COMUM, id));
        var userComumOutData = new UserComumOutDTO(userComum);
        return userComumOutData;
    }
    
}
