package dev.marcus.picPaySimplificado.domain.entities.userComum;

import dev.marcus.picPaySimplificado.domain.entities.userComum.DTOs.UserComumDTO;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Roles;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user_comum")
@Table(name = "users_comuns")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserComum extends Usuario{
    @Column(name = "cpf", unique = true, nullable = false, length = 11)
    private String cpf;

    public UserComum(UserComumDTO userComumData, String senha){
        super(
            userComumData.nome(),
            userComumData.email(),
            senha,
            Roles.COMUM
        );
        setCpf(userComumData.cpf());
    }
}