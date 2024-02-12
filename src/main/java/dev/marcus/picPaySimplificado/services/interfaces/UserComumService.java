package dev.marcus.picPaySimplificado.services.interfaces;

import java.util.List;
import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.userComum.DTOs.UserComumDTO;
import dev.marcus.picPaySimplificado.domain.entities.userComum.DTOs.UserComumOutDTO;

public interface UserComumService {
    List<UserComumOutDTO> getUsersComuns();
    UserComumOutDTO createUserComum(UserComumDTO userComumData);
    UserComumOutDTO getUserComum(UUID id);
}
